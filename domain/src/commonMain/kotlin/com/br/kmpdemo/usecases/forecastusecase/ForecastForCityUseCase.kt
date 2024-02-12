package com.br.kmpdemo.usecases.forecastusecase

import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.usecases.BaseUseCase
import com.br.kmpdemo.usecases.BaseUseCase.*
import com.br.kmpdemo.usecases.forecastusecase.ForecastForCityUseCase.*

interface ForecastForCityUseCase :
    BaseUseCase<Request, Response> {
    data class Request(
        val city: String
    ) : BaseRequest()

    data class Response(
        val forecast: Forecast?
    ) : BaseResponse()

    class InvalidCityName : IllegalArgumentException("City name is invalid")
}