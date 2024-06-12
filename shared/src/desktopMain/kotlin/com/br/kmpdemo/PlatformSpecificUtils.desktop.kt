package com.br.kmpdemo

import com.br.kmpdemo.utils.MeasurementType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent


actual object MeasurementPreference {
    actual var preference: MeasurementType = MeasurementType.IMPERIAL
}

actual class MokoLocationProvider actual constructor() : KoinComponent {
    actual suspend fun startTracking() {}
    actual fun stopTracking() {}
    actual suspend fun getLocationFlow(): Flow<UserLocation> =
        flow { emit(UserLocation()) }
}
