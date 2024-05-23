package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.ForecastDto
import com.br.kmpdemo.models.Forecast

fun ForecastDto?.toForecastModel() = Forecast(
    timelines = this?.timelines.toTimelinesModel(),
    locationDto = this?.locationDto.toLocationModel()
)
