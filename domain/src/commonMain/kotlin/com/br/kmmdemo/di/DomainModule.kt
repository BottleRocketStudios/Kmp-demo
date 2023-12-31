package com.br.kmmdemo.di

import com.br.kmmdemo.network.TomorrowIoService
import com.br.kmmdemo.network.TomorrowIoServiceImpl
import com.br.kmmdemo.network.ktorClient
import com.br.kmmdemo.repositories.WeatherRepoImplementation
import com.br.kmmdemo.repositories.WeatherRepository
import com.br.kmmdemo.usecases.forecastusecase.ForecastForCityInteractor
import com.br.kmmdemo.usecases.forecastusecase.ForecastForCityUseCase
import com.br.kmmdemo.viewmodels.ForecastViewModel
import io.ktor.client.HttpClient
import org.koin.dsl.module

val domainModule = module {
    // Repositories
    single<WeatherRepository> { WeatherRepoImplementation() }

    // Services
    single<TomorrowIoService> { TomorrowIoServiceImpl() }

    // Clients
    single<HttpClient> { ktorClient() }
}

val useCaseModule = module {
    single<ForecastForCityUseCase> { ForecastForCityInteractor() }
}

val viewModelModule = module {
    single { ForecastViewModel() }
}