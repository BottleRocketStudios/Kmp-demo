package com.br.kmpdemo.di

import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProvider
import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProviderImpl
import com.br.kmpdemo.logger.KmpLogger
import com.br.kmpdemo.logger.KmpLoggerImpl
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator
import org.koin.dsl.module

val commonModule = module {
    // Logger
    single<KmpLogger> { KmpLoggerImpl() }

    // Utils
    single<DispatcherProvider> { DispatcherProviderImpl() }
    single<GeminiMediator> { params -> GeminiMediator(params.get(), params.get()) }
}