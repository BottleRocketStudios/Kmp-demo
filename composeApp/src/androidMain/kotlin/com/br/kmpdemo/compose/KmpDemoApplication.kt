<<<<<<<< HEAD:composeApp/src/androidMain/kotlin/com/br/kmpdemo/compose/KmpDemoApplication.kt
package com.br.kmpdemo.compose

import MeasurementPreference
import android.app.Application
========
package com.br.kmpdemo.android

import MeasurementPreference
import android.app.Application
import com.br.kmpdemo.android.di.androidModule
>>>>>>>> refs/heads/feature/ai-chat:androidApp/src/androidMain/kotlin/com/br/kmpdemo/android/KmpDemoApplication.kt
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