package com.br.kmpdemo

import com.br.kmpdemo.utils.MeasurementType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent


expect object MeasurementPreference {
    var preference: MeasurementType
}


expect class MokoLocationProvider(): KoinComponent {
    suspend fun startTracking()
    fun stopTracking()
    suspend fun getLocationFlow(): Flow<UserLocation>
}

object LastKnownLocation {
    val userLocation = MutableStateFlow<UserLocation?>(null)
}

/** Creating UserLocation so the user's location is not confused with the
 * `Location` object returned by the API */
data class UserLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val cityName: String = ""
) {
    fun toCoordinates() = "$latitude,$longitude"
}

