package com.br.kmpdemo.repositories

import com.br.kmpdemo.network.dtos.ForecastDto

interface WeatherRepository {
    suspend fun getForecastForCity(city: String): Result<ForecastDto>
}