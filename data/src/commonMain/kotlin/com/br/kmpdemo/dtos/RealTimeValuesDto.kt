package com.br.kmpdemo.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RealTimeValuesDto(
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val cloudCover: Long?,
    val dewPoint: Double?,
    val freezingRainIntensity: Long?,
    val humidity: Double?,
    val precipitationProbability: Long?,
    val pressureSurfaceLevel: Double?,
    val rainIntensity: Long?,
    val sleetIntensity: Long?,
    val snowIntensity: Long?,
    val temperature: Double?,
    val temperatureApparent: Double?,
    val uvHealthConcern: Long?,
    val uvIndex: Double?,
    val visibility: Double?,
    val weatherCode: Double?,
    val windDirection: Double?,
    val windGust: Double?,
    val windSpeed: Double?,
): Dto
