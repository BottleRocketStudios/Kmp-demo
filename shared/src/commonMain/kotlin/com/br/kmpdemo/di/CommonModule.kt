package com.br.kmpdemo.di

import com.br.kmpdemo.logger.KmpLogger
import com.br.kmpdemo.logger.KmpLoggerImpl
import org.koin.dsl.module

val commonModule = module {
    // Logger
    single<KmpLogger> { KmpLoggerImpl() }
}