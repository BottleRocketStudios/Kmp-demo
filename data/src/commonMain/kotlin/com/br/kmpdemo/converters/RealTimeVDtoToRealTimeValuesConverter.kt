package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.RealTimeValuesDto
import com.br.kmpdemo.models.RealTimeValues


fun RealTimeValuesDto?.toRealTimeValuesModel() = RealTimeValues(
    cloudBase = this?.cloudBase,
    cloudCeiling = this?.cloudCeiling,
    cloudCover = this?.cloudCover,
    dewPoint = this?.dewPoint,
    freezingRainIntensity = this?.freezingRainIntensity,
    humidity = this?.humidity,
    precipitationProbability = this?.precipitationProbability,
    pressureSurfaceLevel = this?.pressureSurfaceLevel,
    rainIntensity = this?.rainIntensity,
    sleetIntensity = this?.sleetIntensity,
    snowIntensity = this?.snowIntensity,
    temperature = this?.temperature,
    temperatureApparent = this?.temperatureApparent,
    uvHealthConcern = this?.uvHealthConcern,
    uvIndex = this?.uvIndex,
    visibility = this?.visibility,
    weatherCode = this?.weatherCode,
    windDirection = this?.windDirection,
    windGust = this?.windGust,
    windSpeed = this?.windSpeed,
)