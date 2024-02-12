package com.br.kmpdemo.repositories

import com.br.kmpdemo.network.TomorrowIoService
import com.br.kmpdemo.network.dtos.ForecastDto
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepoImplementation : WeatherRepository, KoinComponent {
    private val tomorrowIoService: TomorrowIoService by inject()
    override suspend fun getForecastForCity(city: String): Result<ForecastDto> {
        return tomorrowIoService.getForecastByCity(city)
    }
}