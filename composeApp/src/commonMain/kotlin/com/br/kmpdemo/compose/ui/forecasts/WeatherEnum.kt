package com.br.kmpdemo.compose.ui.forecasts

import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

enum class WeatherEnum(val weather: StringResource, val icon: ImageResource) {
    // DAY WEATHER
    DAY_FOG(SharedRes.strings.fog, SharedRes.images.day_fog),
    DAY_PARTLY_CLOUDY(SharedRes.strings.partly_cloudy, SharedRes.images.partly_cloudy),
    DAY_RAIN(SharedRes.strings.rain, SharedRes.images.day_rain),
    DAY_SNOW(SharedRes.strings.snow, SharedRes.images.day_snow),
    DAY_THUNDERSTORM(SharedRes.strings.thunderstorms, SharedRes.images.day_thunderstorm),
    DAY_CLOUDY(SharedRes.strings.cloudy, SharedRes.images.overcast_cloudy),
    SUNNY(SharedRes.strings.sunny, SharedRes.images.sunny),

    // NIGHT WEATHER
    NIGHT_CLEAR(SharedRes.strings.clear_skies, SharedRes.images.night_clear),
    NIGHT_CLOUDY(SharedRes.strings.cloudy, SharedRes.images.night_cloudy),
    NIGHT_FOG(SharedRes.strings.fog, SharedRes.images.night_fog),
    NIGHT_RAIN(SharedRes.strings.rain, SharedRes.images.night_rain),
    NIGHT_SNOW(SharedRes.strings.snow, SharedRes.images.night_snow),
    NIGHT_THUNDERSTORM(SharedRes.strings.thunderstorms, SharedRes.images.night_thunderstorm),

    // INCLEMENT WEATHER
    HAIL(SharedRes.strings.hail, SharedRes.images.hail),
    HEAVY_RAIN(SharedRes.strings.heavy_rain, SharedRes.images.rain_heavy),
    LIGHT_RAIN(SharedRes.strings.light_rain, SharedRes.images.rain_light),

    // CURRENTLY UNUSED
    LIGHTENING(SharedRes.strings.lightening, SharedRes.images.lightening),
    WINDY(SharedRes.strings.windy, SharedRes.images.windy),
}