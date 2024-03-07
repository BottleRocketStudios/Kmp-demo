package com.br.kmpdemo.network

import com.br.kmpdemo.models.ApiException
import com.br.kmpdemo.network.service.TomorrowIoService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
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
        })
    }

    HttpResponseValidator {
        validateResponse { response ->
            if (!response.status.isSuccess()) {
                val error: Error = response.body()
                throw ApiException(
                    code = response.status.value,
                    message = error.message.toString(),
                    subCode = 0,
                    subMessage = "",
                    errorNumber = 0
                )
            }
        }
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = NetworkRoutes.BASE_HOST
            parameters.append(TomorrowIoService.API_KEY, NetworkRoutes.KEY)
        }
    }
}