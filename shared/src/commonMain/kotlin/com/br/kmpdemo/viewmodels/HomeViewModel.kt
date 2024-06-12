package com.br.kmpdemo.viewmodels

import androidx.lifecycle.viewModelScope
import com.bottlerocketstudios.launchpad.google.utils.network.service.airquality.AirQualityApiService
import com.br.kmpdemo.LastKnownLocation
import com.br.kmpdemo.MeasurementPreference
import com.br.kmpdemo.MokoLocationProvider
import com.br.kmpdemo.UserLocation
import com.br.kmpdemo.models.Daily
import com.br.kmpdemo.models.DailyValues
import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.models.Hourly
import com.br.kmpdemo.models.HourlyValues
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.repositories.WeatherRepository
import com.br.kmpdemo.utils.isHour
import com.br.kmpdemo.utils.isToday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    //region DI
    private val weatherRepo: WeatherRepository by inject()
    private val locationProvider: MokoLocationProvider by inject()
    private val airQualityApiService: AirQualityApiService by inject()
    //endregion

    /**region Forecast Responses */
    private val hourlyResponse = MutableStateFlow<Forecast?>(null)
    private val dailyResponse = MutableStateFlow<Forecast?>(null)
    val realTimeWeather = MutableStateFlow<RealTime?>(null)
    val airQualityIndex = MutableStateFlow<Int?>(null)
    //endregion

    /**region UI */
    // Forecasts
    val hourlyForecasts: Flow<List<Hourly?>> = hourlyResponse.map { it?.timelines?.hourly ?: emptyList() }
    val dailyForecasts: Flow<List<Daily?>> = dailyResponse.map { it?.timelines?.daily  ?: emptyList() }
    val currentDaily: Flow<DailyValues?> = dailyForecasts.map { daily -> daily.find {
        it?.time?.isToday() ?: false
    }?.dailyValues }
    val currentHourly: Flow<HourlyValues?> = hourlyForecasts.map { hourly -> hourly.find {
        it?.time?.isHour() ?: false
    }?.hourlyValues }

    // Metric/Imperial
    val measurementPref = MutableStateFlow(MeasurementPreference.preference)

    //  Location
    val userLocation = LastKnownLocation.userLocation.map { it?.cityName ?: "" }
    val shouldShowPermissionsDialog = MutableStateFlow(true)
    //endregion

    /**region Network calls */
    private fun getDailyForecasts(location: String) =
        launchIO {
            weatherRepo.getDailyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { dailyResponse.value = it }
                .onFailure { log.e("[getDailyForecasts]", "Failure: ${it.message}") }
        }

    private fun getHourlyForecasts(location: String) =
        launchIO {
            weatherRepo.getHourlyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { hourlyResponse.value = it }
                .onFailure { log.e("[getHourlyForecasts]",  "Failure: ${it.message}" ) }
        }

    private fun getRealTimeForecasts(location: String) =
        launchIO {
            weatherRepo.getRealTimeForecast(location, units = measurementPref.value.type)
                .onSuccess { realTimeWeather.value = it }
                .onFailure { log.e("[getRealTimeForecasts]",  "Failure: ${it.message}" ) }
        }

    // TODO: ASAA-196 Add details for AirQualityWidget "See More" navigation
    private fun getAirQualityDetails(location: UserLocation) =
        launchIO {
            try {
                airQualityIndex.value = airQualityApiService
                    .getCurrentAqiConditions(location.latitude, location.longitude)
                    .aqiConditions?.find { it.code == "usa_epa" }
                    ?.aqi
            } catch (e: Exception) {
                log.e("[onLocationPermissionsGranted]",  "Failure: ${e.message}" )
            }
        }
    //endregion

    /**region Permissions Utils */
    fun onDismissLocationPermissionDialog(permissionGranted: Boolean = false) {
        if (permissionGranted) {
            shouldShowPermissionsDialog.value = false
            getLocation()
        }
    }

    private fun getLocation() {
        // Needs to run on main thread
        viewModelScope.launch {
            locationProvider.startTracking()
            LastKnownLocation.userLocation.value = locationProvider.getLocationFlow().first()
            locationProvider.stopTracking()
        }
    }

    init {
        launchIO {
            LastKnownLocation.userLocation.collect { location ->
                location?.let {
                    getDailyForecasts(it.toCoordinates())
                    getHourlyForecasts(it.toCoordinates())
                    getRealTimeForecasts(it.toCoordinates())
                    getAirQualityDetails(it)
                }
            }
        }
    }
    //endregion
}