package com.br.kmpdemo.models.converters

import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.network.dtos.ForecastDto

fun ForecastDto?.toForecastModel(): Forecast {
    return Forecast(
        timelines = this?.timelines.toTimelinesModel(),
        locationDto = this?.locationDto.toLocationModel()
    )
}