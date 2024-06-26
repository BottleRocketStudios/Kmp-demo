package com.br.kmpdemo.compose.ui.weatherDetails.pressure

import androidx.compose.runtime.Composable
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface


@Composable
fun BarometricPressureWidget(pressure: Float) {
    WeatherDetailsSurface(
        content = {
            DetailsWidgetLabel(
                icon = SharedRes.images.pressure_icon,
                iconDesc = SharedRes.strings.pressure,
                label = SharedRes.strings.pressure,
            )

            BarometerGauge(pressure)
        }
    )
}