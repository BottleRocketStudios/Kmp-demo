package com.br.kmpdemo.network

import com.br.kmpdemo.network.dtos.ForecastDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TomorrowIoServiceImpl : TomorrowIoService, KoinComponent {
    private val client: HttpClient by inject()

    override suspend fun getForecastByCity(city: String) =
        runCatching {
            client.get {
                url(path = "/forecast") {
                    parameters["location"] = city
                }
            }.body<ForecastDto>()
        }
}
