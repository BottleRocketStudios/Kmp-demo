package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeValues(
    val cloudBase: Double? = null,
    val cloudCeiling: Double? = null,
    val cloudCover: Long? = null,
    val dewPoint: Double? = null,
    val freezingRainIntensity: Long? = null,
    val humidity: Double? = null,
    val precipitationProbability: Long? = null,
    val pressureSurfaceLevel: Double? = null,
    val rainIntensity: Long? = null,
    val sleetIntensity: Long? = null,
    val snowIntensity: Long? = null,
    val temperature: Double? = null,
    val temperatureApparent: Double? = null,
    val uvHealthConcern: Long? = null,
    val uvIndex: Double? = null,
    val visibility: Double? = null,
    val weatherCode: Double? = null,
    val windDirection: Double? = null,
    val windGust: Double? = null,
    val windSpeed: Double? = null,
): DomainModel
