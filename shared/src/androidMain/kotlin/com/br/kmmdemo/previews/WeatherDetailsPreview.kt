package com.br.kmmdemo.previews

import FeelsLikeWidget
import VisibilityWidget
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmmdemo.theme.KMMTheme
import com.br.kmmdemo.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmmdemo.ui.weatherDetails.airQuality.AirQualityWidget
import com.br.kmmdemo.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmmdemo.ui.weatherDetails.humidity.HumidityState
import com.br.kmmdemo.ui.weatherDetails.humidity.HumidityWidget
import com.br.kmmdemo.ui.weatherDetails.rainFall.RainFallState
import com.br.kmmdemo.ui.weatherDetails.rainFall.RainFallWidget
import com.br.kmmdemo.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmmdemo.ui.weatherDetails.sunrise_sunset.SunriseSunsetWidget
import com.br.kmmdemo.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmmdemo.ui.weatherDetails.uvIndex.UvIndexWidget
import com.br.kmmdemo.ui.weatherDetails.visibility.VisibilityState
import com.br.kmmdemo.ui.weatherDetails.wind.WindDirection
import com.br.kmmdemo.ui.weatherDetails.wind.WindState
import com.br.kmmdemo.ui.weatherDetails.wind.WindWidget

@Composable
@Preview
fun AirQualityPreview() {
    KMMTheme {
        Column {
            AirQualityWidget(AirQualityEnum.YELLOW)
            // No Air Quality Info Returned
            AirQualityWidget(AirQualityEnum.UNKNOWN)
        }
    }
}

@Composable
@Preview
fun UVIndexPreview() {
    KMMTheme {
        Row {
            UvIndexWidget(UVIndexEnum.MODERATE)
            UvIndexWidget(UVIndexEnum.UNKNOWN)
        }
    }
}

@Composable
@Preview
fun FeelsLikePreview() {
    KMMTheme {
        Row {
            FeelsLikeWidget(FeelsLikeState(75.0, 65.0))
            FeelsLikeWidget(FeelsLikeState(null, 59.0))
        }
    }
}

@Composable
@Preview
fun HumidityPreview() {
    KMMTheme {
        Row {
            HumidityWidget(HumidityState(90.0, 17.0))
            HumidityWidget(HumidityState(null, null))
        }
    }
}

@Composable
@Preview
fun RainFallPreview() {
    KMMTheme {
        Row {
            RainFallWidget(RainFallState("1.8 mm", "1.2 mm"))
            RainFallWidget(RainFallState())
        }
    }
}

@Composable
@Preview
fun VisibilityPreview() {
    KMMTheme {
        Row {
            VisibilityWidget(VisibilityState("8 km", true))
            VisibilityWidget(VisibilityState())
        }
    }
}

@Composable
@Preview
fun SunriseSunsetPreview() {
    KMMTheme {
        Column {
            Row {
                SunriseSunsetWidget(SunriseSunsetState(localTime = "13:00", sunriseTime = "4:58", sunsetTime = "17:35"))
                SunriseSunsetWidget(SunriseSunsetState(localTime = "14:20", sunriseTime = "5:25", sunsetTime = "18:02"))
            }
            Row {
                SunriseSunsetWidget(SunriseSunsetState(localTime = "14:45", sunriseTime = "6:14", sunsetTime = "18:15"))
                SunriseSunsetWidget(SunriseSunsetState(localTime = "20:23", sunriseTime = "7:23", sunsetTime = "19:04"))
            }
            Row {
                SunriseSunsetWidget(SunriseSunsetState(localTime = null, sunriseTime = null, sunsetTime = null))
            }
        }
    }
}

@Composable
@Preview
fun WindPreview() {
    KMMTheme {
        Column {
            Row {
                WindWidget(WindState(windDirection = WindDirection.N, windSpeed = "9.7"))
                WindWidget(WindState(windDirection = WindDirection.ESE, windSpeed = "3.2"))
            }
            Row {
                WindWidget(WindState(windDirection = WindDirection.W, windSpeed = "9.7"))
                WindWidget(WindState(windDirection = WindDirection.WNW, windSpeed = "3.2"))
            }
            Row {
                WindWidget(WindState(windDirection = WindDirection.SSE, windSpeed = "9.7"))
                WindWidget(WindState(windDirection = WindDirection.N))
            }
        }
    }
}