package com.br.kmpdemo.di

import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProvider
import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProviderImpl
import com.bottlerocketstudios.launchpad.utils.logger.NapierLogger
import com.bottlerocketstudios.launchpadutilsdomain.logger.LoggingManager
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator
import org.koin.dsl.module

val commonModule = module {
    // Logger
    single<LoggingManager> { NapierLogger() }

    // Utils
    single<DispatcherProvider> { DispatcherProviderImpl() }
    single<GeminiMediator> { params -> GeminiMediator(params.get(), params.get()) }
}