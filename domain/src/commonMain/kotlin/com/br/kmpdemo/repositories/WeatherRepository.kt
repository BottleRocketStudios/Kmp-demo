package com.br.kmpdemo.repositories

import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.models.RealTime

interface WeatherRepository {
//    suspend fun getForecastForCity(city: String): Result<Forecast>
    suspend fun getDailyForecast(location: String, units: String): Result<Forecast?>
    suspend fun getHourlyForecast(location: String, units: String): Result<Forecast?>
    suspend fun getRealTimeForecast(location: String, units: String): Result<RealTime?>
}