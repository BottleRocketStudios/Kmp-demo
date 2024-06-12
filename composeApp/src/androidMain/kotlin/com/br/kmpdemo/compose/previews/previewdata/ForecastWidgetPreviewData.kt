package com.br.kmpdemo.compose.previews.previewdata

import com.br.kmpdemo.compose.widget.forecast.ForecastWidgetState

val forecastWidgetPreviewData = ForecastWidgetState(
    location = "New York City",
    currentTemperature = 32.0,

    dailyPrecipitationProbabilityAvg = 60.0,
    dailyCloudCoverAvg = 0.0,
    dailyTemperatureApparentMin = 26.0,
    dailyTemperatureApparentMax = 34.0,

    futureTemperature1 = 28.0,
    futurePrecipitationProbability1 = 0.0,
    futureCloudCover1 = 0.0,

    futureTemperature2 = 30.0,
    futurePrecipitationProbability2 = 50.0,
    futureCloudCover2 = 0.0,

    futureTemperature3 = 32.0,
    futurePrecipitationProbability3 = 0.0,
    futureCloudCover3 = 50.0,
)