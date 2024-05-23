package com.br.kmpdemo.network

import com.br.kmpdemo.network.service.TomorrowIoService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun ktorClient(): HttpClient = HttpClient {
    expectSuccess = true

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        })
    }


    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = NetworkRoutes.BASE_HOST
            parameters.append(TomorrowIoService.API_KEY, NetworkRoutes.KEY)
        }
    }
}