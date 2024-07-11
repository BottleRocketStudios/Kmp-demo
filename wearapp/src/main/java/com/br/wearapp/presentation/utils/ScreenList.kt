package com.br.wearapp.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.wearapp.presentation.mocks.MockData
import com.br.wearapp.presentation.ui.CurrentTemp

@Composable
fun getScreenList(modifier: Modifier = Modifier): List<Unit> {
    return listOf(CurrentTemp(state = MockData.getMockCurrentTempState(), modifier))
}