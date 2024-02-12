package com.br.kmpdemo.compose.ui.weatherDetails.pressure

data class BarometricPressureState(
    // Double returned from API will need to be converted
    // to a Float in the range of 0F..1F
    val pressure: Float? = null
)
