package com.br.kmpdemo.network

import com.br.kmpdemo.network.dtos.ForecastDto

interface TomorrowIoService {
    suspend fun getForecastByCity(city: String): Result<ForecastDto>
}