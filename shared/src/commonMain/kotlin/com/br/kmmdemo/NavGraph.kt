package com.br.kmmdemo

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.transition.NavTransition

fun RouteBuilder.navGraph() {
    scene("/home", navTransition = NavTransition()) {
        HomeScreen()
    }
    scene("/map", navTransition = NavTransition()) {
        WeatherMapScreen()
    }
}

@Composable
expect fun WeatherMapScreen()