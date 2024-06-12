package com.br.kmpdemo

import dev.icerock.moko.geo.LocationTracker
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual class MokoLocationProvider actual constructor() : KoinComponent {
    val locationTracker: LocationTracker by inject()

    actual suspend fun getLocationFlow() =
        locationTracker.getLocationsFlow().distinctUntilChanged().map {
            UserLocation(it.latitude, it.longitude)
        }

    actual suspend fun startTracking() = locationTracker.startTracking()
    actual fun stopTracking() = locationTracker.stopTracking()
}