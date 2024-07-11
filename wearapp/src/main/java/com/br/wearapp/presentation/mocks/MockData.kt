package com.br.wearapp.presentation.mocks

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.utils.MeasurementType
import com.br.wearapp.presentation.ui.CurrentTempState
import kotlin.random.Random

object MockData {
    private fun getMockTemp() = Random.nextDouble(from = 0.00, until = 115.00)
    private fun getMockMeasurementPref() = MeasurementType.entries.random()

    fun getMockCurrentTempState(): CurrentTempState = CurrentTempState(
        temperature = mutableDoubleStateOf(getMockTemp()),
        measurementPref = mutableStateOf(getMockMeasurementPref()),
    )
}