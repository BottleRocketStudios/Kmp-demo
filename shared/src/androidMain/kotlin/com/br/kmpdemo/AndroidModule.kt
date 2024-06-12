package com.br.kmpdemo

import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController
import org.koin.dsl.module


val androidModule = module {
    single<LocationTracker> { LocationTracker(PermissionsController(applicationContext = get())) }
}