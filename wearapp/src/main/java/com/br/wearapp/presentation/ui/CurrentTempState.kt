package com.br.wearapp.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.utils.MeasurementType

data class CurrentTempState(
    val temperature: State<Double?> = mutableStateOf(null),
    val measurementPref: State<MeasurementType?> = mutableStateOf(null),
)
