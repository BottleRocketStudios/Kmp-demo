package com.br.kmpdemo.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json

fun ktorClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
        })
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = NetworkRoutes.BASE_HOST
            headers {
                appendIfNameAbsent("apikey", NetworkRoutes.API_KEY)
            }
        }
    }
}