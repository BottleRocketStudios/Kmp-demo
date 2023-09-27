package com.br.kmmdemo.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class DailyDataPointValuesDto(
    val cloudBaseAvg: Float?,
    val cloudBaseMax: Float?,
    val cloudBaseMin: Int?,
    val cloudCeilingAvg: Float?,
    val cloudCeilingMax: Float?,
    val cloudCeilingMin: Int?,
    val cloudCoverAvg: Float?,
    val cloudCoverMax: Int?,
    val cloudCoverMin: Int?,
    val dewPointAvg: Float?,
    val dewPointMax: Float?,
    val dewPointMin: Float?,
    val evapotranspirationAvg: Float?,
    val evapotranspirationMax: Float?,
    val evapotranspirationMin: Float?,
    val freezingRainIntensityAvg: Int?,
    val freezingRainIntensityMax: Int?,
    val freezingRainIntensityMin: Int?,
    val humidityAvg: Float?,
    val humidityMax: Float?,
    val humidityMin: Int?,
    val iceAccumulationAvg: Int?,
    val iceAccumulationLweAvg: Int?,
    val iceAccumulationLweMax: Int?,
    val iceAccumulationLweMin: Int?,
    val iceAccumulationLweSum: Int?,
    val iceAccumulationMax: Int?,
    val iceAccumulationMin: Int?,
    val iceAccumulationSum: Int?,
    val moonriseTime: String?,
    val moonsetTime: String?,
    val precipitationProbabilityAvg: Int?,
    val precipitationProbabilityMax: Int?,
    val precipitationProbabilityMin: Int?,
    val pressureSurfaceLevelAvg: Int?,
    val pressureSurfaceLevelMax: Int?,
    val pressureSurfaceLevelMin: Int?,
    val rainAccumulationAvg: Int?,
    val rainAccumulationLweAvg: Int?,
    val rainAccumulationLweMax: Int?,
    val rainAccumulationLweMin: Int?,
    val rainAccumulationMax: Int?,
    val rainAccumulationMin: Int?,
    val rainAccumulationSum: Int?,
    val rainIntensityAvg: Int?,
    val rainIntensityMax: Int?,
    val rainIntensityMin: Int?,
    val sleetAccumulationAvg: Int?,
    val sleetAccumulationLweAvg: Int?,
    val sleetAccumulationLweMax: Int?,
    val sleetAccumulationLweMin: Int?,
    val sleetAccumulationLweSum: Int?,
    val sleetAccumulationMax: Int?,
    val sleetAccumulationMin: Int?,
    val sleetIntensityAvg: Int?,
    val sleetIntensityMax: Int?,
    val sleetIntensityMin: Int?,
    val snowAccumulationAvg: Float?,
    val snowAccumulationLweAvg: Int?,
    val snowAccumulationLweMax: Float?,
    val snowAccumulationLweMin: Int?,
    val snowAccumulationLweSum: Float?,
    val snowAccumulationMax: Float?,
    val snowAccumulationMin: Int?,
    val snowAccumulationSum: Int?,
    val snowDepthAvg: Float?,
    val snowDepthMax: Float?,
    val snowDepthMin: Float?,
    val snowDepthSum: Float?,
    val snowIntensityAvg: Int?,
    val snowIntensityMax: Int?,
    val snowIntensityMin: Int?,
    val sunriseTime: String?,
    val sunsetTime: String?,
    val temperatureApparentAvg: Float?,
    val temperatureApparentMax: Float?,
    val temperatureApparentMin: Float?,
    val temperatureAvg: Float?,
    val temperatureMax: Float?,
    val uvHealthConcernAvg: Float?,
    val uvHealthConcernMax: Float?,
    val uvHealthConcernMin: Float?,
    val uvIndexAvg: Int?,
    val uvIndexMax: Int?,
    val uvIndexMin: Int?,
    val visibilityAvg: Int?,
    val visibilityMax: Int?,
    val visibilityMin: Int?,
    val weatherCodeMax: Int?,
    val weatherCodeMin: Int?,
    val windDirectionAvg: Float?,
    val windGustAvg: Float?,
    val windGustMax: Float?,
    val windGustMin: Float?,
    val windSpeedAvg: Float?,
    val windSpeedMax: Float?,
    val windSpeedMin: Float?
)
