package com.br.wearapp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.wearapp.presentation.ui.HomeScreen

@Composable
fun getScreenList(modifier: Modifier = Modifier): List<Unit> {
    return listOf(HomeScreen(modifier))
}