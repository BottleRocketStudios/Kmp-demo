package com.br.kmpdemo

import com.br.kmpdemo.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class GreetingHelper : KoinComponent {
    fun greet() : String = "greeting.greeting()"
}

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}