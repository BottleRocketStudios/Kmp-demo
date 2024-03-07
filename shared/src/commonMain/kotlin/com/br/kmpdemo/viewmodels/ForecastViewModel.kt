package com.br.kmpdemo.viewmodels

import org.koin.core.component.KoinComponent

class ForecastViewModel : KoinComponent {
    // TODO NEED TO LOOK INTO VIEW MODEL OPTION

//    val dispatcher = Dispatchers.Default
//    private val forecastUseCase: ForecastForCityUseCase by inject()
//
//    val forecast: MutableStateFlow<Forecast?> = MutableStateFlow(null)
//
//    fun fetchForecast() {
//        CoroutineScope(dispatcher).launch {
//            forecast.value =
//                forecastUseCase(ForecastForCityUseCase.Request("Dallas")).getOrNull()?.forecast
//        }
//    }
}