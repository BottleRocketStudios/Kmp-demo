package com.br.kmpdemo.network

/**
 * Format = https://api.tomorrow.io/v4/weather/forecast?location={LOCATION}&timesteps={TIMESTEP}&units={UNITS}&apikey={API_KEY}
 * - LOCATION format can be:
 *      city name (location=new york)
 *      lat/long pair (location=42.3478, -71.0466)
 *      zip code (location=10029)
 * - For TIMESTEP, use [DAILY_FORECAST], [HOURLY_FORECAST], or [REALTIME_FORECAST]
 *      Must use [DAILY_FORECAST] to access some fields like sunriseTime and sunsetTime
 *      Use [HOURLY_FORECAST] to access hourly forecast data
 *      Use [REALTIME_FORECAST] to access current weather data for WeatherWidget
 **/
object NetworkRoutes {
    const val BASE_URL = "https://api.tomorrow.io/v4/weather"
    const val BASE_HOST = "api.tomorrow.io/v4/weather"
    const val FORECAST_URL = "/forecast"
    const val REALTIME_URL = "/realtime"
    const val KEY = "64JPsK8f3qpZkvaqAlToKoi0HMIv3ydf"
    const val DAILY_FORECAST = "1d"
    const val HOURLY_FORECAST = "1h"
    @Suppress("MemberVisibilityCanBePrivate")
    const val REALTIME_FORECAST = "current"
}