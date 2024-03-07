package com.br.kmpdemo.repository

import com.br.kmpdemo.converters.toForecastModel
import com.br.kmpdemo.converters.toRealTimeModel
import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.network.service.TomorrowIoService
import com.br.kmpdemo.repositories.WeatherRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepoImplementation : WeatherRepository, KoinComponent {
    private val tomorrowIoService: TomorrowIoService by inject()

//    override suspend fun getForecastForCity(city: String): Result<Forecast> =
//        tomorrowIoService
//            .getForecastByCity(city)
//            .map { it.toForecastModel() }

    override suspend fun getDailyForecast(location: String, units: String): Result<Forecast?> =
        tomorrowIoService
            .getDailyForecast(location, units)
            .map { it.toForecastModel() }

    override suspend fun getHourlyForecast(location: String, units: String): Result<Forecast?> =
        tomorrowIoService
            .getHourlyForecast(location, units)
            .map { it.toForecastModel() }

    override suspend fun getRealTimeForecast(location: String, units: String): Result<RealTime> =
        tomorrowIoService
            .getRealTimeForecast(location, units)
            .map { it.toRealTimeModel() }
}