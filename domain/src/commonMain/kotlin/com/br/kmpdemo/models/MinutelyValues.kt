package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class MinutelyValues(
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val cloudCover: Double?,
    val dewPoint: Double?,
    val freezingRainIntensity: Double?,
    val humidity: Double?,
    val precipitationProbability: Double?,
    val pressureSurfaceLevel: Double?,
    val rainIntensity: Double?,
    val sleetIntensity: Double?,
    val snowIntensity: Double?,
    val temperature: Double?,
    val temperatureApparent: Double?,
    val uvHealthConcern: Double?,
    val uvIndex: Double?,
    val visibility: Double?,
    val weatherCode: Double?,
    val windDirection: Double?,
    val windGust: Double?,
    val windSpeed: Double?
): DomainModel
