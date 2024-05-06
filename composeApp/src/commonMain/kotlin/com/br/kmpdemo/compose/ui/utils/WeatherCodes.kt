package com.br.kmpdemo.compose.ui.utils

import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_CLOUDY
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_FOG
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_PARTLY_CLOUDY
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_RAIN
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_SNOW
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.DAY_THUNDERSTORM
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.HAIL
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.HEAVY_RAIN
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.LIGHT_RAIN
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_CLEAR
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_CLOUDY
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_FOG
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_RAIN
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_SNOW
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.NIGHT_THUNDERSTORM
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum.SUNNY

object WeatherCodes {
    private val clearDay = listOf(1000.0, 10000.0, 2101.0, 21010.0, 2106.0, 21060.0)
    private val clearNight = listOf(10001.0, 11031.0)
    private val partlyCloudy = listOf(
        1100.0, 11000.0, 1101.0, 11010.0, 1103.0, 11030.0, 2102.0, 21020.0, 2107.0, 21070.0
    )
    private val dayCloudy =
        listOf(1102.0, 11020.0, 1001.0, 10010.0, 2103.0, 21030.0, 2108.0, 21080.0)
    private val nightCloudy = listOf(11001.0, 11011.0, 11021.0, 10011.0, 21081.0)
    private val dayFog = listOf(2000.0, 20000.0, 2100.0, 21000.0)
    private val nightFog = listOf(
        20001.0, 21001.0, 21011.0, 21011.0, 21021.0, 21031.0, 21061.0, 21071.0
    )
    private val lightRain = listOf(
        4000.0, 40000.0, 40001.0, 4200.0, 42000.0, 42001.0, 4203.0, 42030.0, 42031.0, 4204.0,
        42040.0, 42041.0, 4205.0, 42050.0, 42051.0, 4214.0, 42140.0, 42141.0, 4215.0, 42150.0,
        42151.0
    )
    private val dayRain =
        listOf(4001.0, 40010.0, 4209.0, 42090.0, 42091.0, 4208.0, 42080.0, 4210.0, 42100.0)
    private val nightRain = listOf(40011.0, 42081.0, 42101.0)
    private val heavyRain = listOf(
        4201.0, 42010.0, 42011.0, 4211.0, 42110.0, 42111.0,
        4202.0, 42020.0, 42021.0, 4212.0, 42120.0, 42121.0
    )
    private val daySnow = listOf(
        5000.0, 50000.0, 5001.0, 50010.0, 5100.0, 51000.0, 5101.0, 51010.0, 5115.0, 51150.0,
        5116.0, 51160.0, 5117.0, 51170.0, 5122.0, 51220.0, 5102.0, 51020.0, 5103.0, 51030.0,
        5104.0, 51040.0, 5105.0, 51050.0, 5106.0, 51060.0, 5107.0, 51070.0, 5119.0, 51191.0,
        5120.0, 51200.0, 5121.0, 51210.0, 5110.0, 51100.0, 5108.0, 51080.0, 5114.0, 51140.0,
        5112.0, 51120.0,
    )
    private val nightSnow = listOf(
        50001.0, 50011.0, 51001.0, 51011.0, 51151.0, 51161.0, 51171.0, 51221.0, 51021.0, 51031.0,
        51041.0, 51051.0, 51061.0, 51071.0, 51191.0, 51201.0, 51211.0, 51101.0, 51081.0, 51141.0,
        51121.0
    )
    private val dayStorm = listOf(
        8000.0, 80000.0, 8001.0, 80010.0, 8003.0, 80030.0, 8002.0, 80020.0
    )
    private val nightStorm = listOf(80001.0, 80011.0, 80031.0, 80021.0)

    fun Double.getWeatherFromCode(): WeatherEnum? {
        return when {
            clearDay.contains(this) -> SUNNY
            clearNight.contains(this) -> NIGHT_CLEAR
            dayCloudy.contains(this) -> DAY_CLOUDY
            partlyCloudy.contains(this) -> DAY_PARTLY_CLOUDY
            nightCloudy.contains(this) -> NIGHT_CLOUDY
            dayFog.contains(this) -> DAY_FOG
            nightFog.contains(this) -> NIGHT_FOG
            lightRain.contains(this) -> LIGHT_RAIN
            dayRain.contains(this) -> DAY_RAIN
            nightRain.contains(this) -> NIGHT_RAIN
            heavyRain.contains(this) -> HEAVY_RAIN
            daySnow.contains(this) -> DAY_SNOW
            nightSnow.contains(this) -> NIGHT_SNOW
            dayStorm.contains(this) -> DAY_THUNDERSTORM
            nightStorm.contains(this) -> NIGHT_THUNDERSTORM
            toString().startsWith("6") || toString().startsWith("7") -> HAIL
            else -> null
        }
    }
}