package com.br.kmpdemo.compose

import com.br.kmpdemo.MeasurementPreference
import android.app.Application
import com.br.kmpdemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KmpDemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KmpDemoApplication)
            androidLogger()
            modules(appModule())
        }
        MeasurementPreference.init(this)
    }
}