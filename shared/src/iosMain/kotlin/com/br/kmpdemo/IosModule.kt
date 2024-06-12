package com.br.kmpdemo

import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.ios.PermissionsController
import org.koin.dsl.module
import platform.CoreLocation.kCLLocationAccuracyBest

val iosModule = module {
    single<LocationTracker> { LocationTracker(PermissionsController(), kCLLocationAccuracyBest) }
}