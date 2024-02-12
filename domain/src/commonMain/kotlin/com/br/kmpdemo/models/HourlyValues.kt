package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class HourlyValues(
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val cloudCover: Double?,
    val dewPoint: Double?,
    val evapotranspiration: Double?,
    val freezingRainIntensity: Double?,
    val humidity: Double?,
    val iceAccumulation: Double?,
    val iceAccumulationLwe: Double?,
    val precipitationProbability: Double?,
    val pressureSurfaceLevel: Double?,
    val rainAccumulation: Double?,
    val rainAccumulationLwe: Double?,
    val rainIntensity: Double?,
    val sleetAccumulation: Double?,
    val sleetAccumulationLwe: Double?,
    val sleetIntensity: Double?,
    val snowAccumulation: Double?,
    val snowAccumulationLwe: Double?,
    val snowDepth: Double?,
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
