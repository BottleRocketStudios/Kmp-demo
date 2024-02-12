package com.br.kmpdemo.viewmodels

import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.usecases.forecastusecase.ForecastForCityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForecastViewModel : KoinComponent {
    // TODO NEED TO LOOK INTO VIEW MODEL OPTION

    val dispatcher = Dispatchers.Default
    private val forecastUseCase: ForecastForCityUseCase by inject()

    val forecast: MutableStateFlow<Forecast?> = MutableStateFlow(null)

    fun fetchForecast() {
        CoroutineScope(dispatcher).launch {
            forecast.value =
                forecastUseCase(ForecastForCityUseCase.Request("Dallas")).getOrNull()?.forecast
        }
    }
}