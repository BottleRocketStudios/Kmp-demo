package com.br.kmpdemo

import com.br.kmpdemo.utils.MeasurementType
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent


expect object MeasurementPreference {
    var preference: MeasurementType
}

expect class KmpLocationProvider(): KoinComponent {
    suspend fun getUsersLocation()
}

object LastKnownLocation {
    var userLocation = MutableStateFlow<UserLocation?>(null)
    fun setLocation(location: UserLocation) {
        userLocation.value = location
    }
}

/** Creating UserLocation so the user's location is not confused with the
 * `Location` object returned by the API */
data class UserLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val cityName: String = ""
) {
    fun toCoordinates() = "$latitude, $longitude"
}

