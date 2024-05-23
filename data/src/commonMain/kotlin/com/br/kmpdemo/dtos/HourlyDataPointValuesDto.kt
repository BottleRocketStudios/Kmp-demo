package com.br.kmpdemo.dtos

import kotlinx.serialization.Serializable

@Serializable
data class HourlyDataPointValuesDto(
    val cloudBase: Double? = null,
    val cloudCeiling: Double? = null,
    val cloudCover: Double? = null,
    val dewPoint: Double? = null,
    val evapotranspiration: Double? = null,
    val freezingRainIntensity: Double? = null,
    val humidity: Double? = null,
    val iceAccumulation: Double? = null,
    val iceAccumulationLwe: Double? = null,
    val precipitationProbability: Double? = null,
    val pressureSurfaceLevel: Double? = null,
    val rainAccumulation: Double? = null,
    val rainAccumulationLwe: Double? = null,
    val rainIntensity: Double? = null,
    val sleetAccumulation: Double? = null,
    val sleetAccumulationLwe: Double? = null,
    val sleetIntensity: Double? = null,
    val snowAccumulation: Double? = null,
    val snowAccumulationLwe: Double? = null,
    val snowDepth: Double? = null,
    val snowIntensity: Double? = null,
    val temperature: Double? = null,
    val temperatureApparent: Double? = null,
    val uvHealthConcern: Double? = null,
    val uvIndex: Double? = null,
    val visibility: Double? = null,
    val weatherCode: Double? = null,
    val windDirection: Double? = null,
    val windGust: Double? = null,
    val windSpeed: Double? = null
): Dto
