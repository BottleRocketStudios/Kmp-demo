package com.br.kmpdemo
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.os.Looper
//import co.touchlab.kermit.Logger
import com.br.kmpdemo.utils.Constants.IS_METRIC
import com.br.kmpdemo.utils.Constants.MEASUREMENT_PREFS
import com.br.kmpdemo.utils.MeasurementType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual object MeasurementPreference {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(MEASUREMENT_PREFS, Context.MODE_PRIVATE)
    }

    actual var preference: MeasurementType
        get() = if (preferences.getBoolean(IS_METRIC, false)) MeasurementType.IMPERIAL else MeasurementType.METRIC
        set(value) {
            preferences.edit().putBoolean(IS_METRIC, value == MeasurementType.METRIC).apply()
        }
}

actual class KmpLocationProvider: KoinComponent {
    private val context: Context by inject()
    private var locationClient: FusedLocationProviderClient? = null
    private var geoCoder: Geocoder? = null

    fun init() {
        locationClient = LocationServices.getFusedLocationProviderClient(context)
        geoCoder = Geocoder(context)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            if (locationResult.locations.isNotEmpty()) {
                locationResult.locations.firstOrNull()?.let { location ->
                    reverseGeoCode(location.latitude, location.longitude)
                }
                // Cancel updates once the location is returned and set in the LastKnownLocation object
                locationClient?.removeLocationUpdates(this)
            }
        }
    }

    @SuppressLint("MissingPermission")
    actual suspend fun getUsersLocation() {
        try {
            locationClient?.requestLocationUpdates(
                LocationRequest.create(),
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: Exception) {
//            FIXME - Get logging working again
//            Logger.e("[getLastKnownLocation]") { "Last Location Failure: ${e.message}" }
        }
    }

    /// FIXME: update to non-deprecated version of `getFromLocation` method (Requires 33)
    /** Reverse geocoding is necessary because the location name returned from API is unreliable */
    private fun reverseGeoCode(lat: Double, lon: Double) {
        try {
            val location = geoCoder?.getFromLocation(lat, lon, 1)
            location?.get(0)?.let {
                LastKnownLocation.setLocation(UserLocation(lat, lon, it.locality))
            }
        } catch (e: Exception) {
//            FIXME - Get logging working again
//            Logger.e("[reverseGeoCode]") { "Reverse Geocoding Failed: ${e.message}" }
        }
    }
}