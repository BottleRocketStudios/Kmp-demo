package com.br.kmpdemo.compose.widget.forecast

data class ForecastWidgetState(
    val location: String,
    val currentTemperature: Double,
    val dailyTemperatureApparentMin: Double,
    val dailyTemperatureApparentMax: Double,
    val dailyPrecipitationProbabilityAvg: Double,
    val dailyCloudCoverAvg: Double,
    val futureTemperature1: Double,
    val futurePrecipitationProbability1: Double,
    val futureCloudCover1: Double,
    val futureTemperature2: Double,
    val futurePrecipitationProbability2: Double,
    val futureCloudCover2: Double,
    val futureTemperature3: Double,
    val futurePrecipitationProbability3: Double,
    val futureCloudCover3: Double,
)