package com.br.kmpdemo.di

import com.bottlerocketstudios.launchpad.google.utils.network.service.airquality.AirQualityApiService
import com.br.kmpdemo.MokoLocationProvider
import com.br.kmpdemo.network.ktorClient
import com.br.kmpdemo.network.service.TomorrowIoService
import com.br.kmpdemo.network.service.TomorrowIoService.Companion.GOOGLE_MAPS_API_KEY
import com.br.kmpdemo.repositories.WeatherRepository
import com.br.kmpdemo.repository.WeatherRepoImplementation
import com.br.kmpdemo.useCases.ForecastForCityInteractor
import com.br.kmpdemo.usecases.forecastusecase.ForecastForCityUseCase
import io.ktor.client.HttpClient
import org.koin.dsl.module

// TODO - Look into moving some of this into data module ??
val domainModule = module {
    // Repositories
    single<WeatherRepository> { WeatherRepoImplementation() }

    // Services
    single<TomorrowIoService> { TomorrowIoService() }

    // Clients
    single<HttpClient> { ktorClient() }
    single { MokoLocationProvider() }
}

val useCaseModule = module {
    single<ForecastForCityUseCase> { ForecastForCityInteractor() }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepoImplementation() }
    single<AirQualityApiService> { AirQualityApiService(GOOGLE_MAPS_API_KEY) }
}