package com.br.kmpdemo.compose.ui.aichat

import com.br.kmpdemo.nav.NavRoutes
import com.br.kmpdemo.viewmodels.AiChatViewModel
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel

fun RouteBuilder.aiChatComposable(navigator: Navigator) {
    scene(route = NavRoutes.AICHAT, navTransition = NavTransition()) {
        val viewModel = viewModel(AiChatViewModel::class) {
            AiChatViewModel()
        }

        val state = viewModel.toState()

        AiChatScreen(state)
    }
}