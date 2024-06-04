package com.br.kmpdemo.compose.ui.aichat

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.viewmodels.AiChatViewModel

fun NavGraphBuilder.aiChatComposable(navigator: NavController) {
    composable(route = NavRoutes.AICHAT) {
        val viewModel = viewModel { AiChatViewModel() }

        val state = viewModel.toState()

        AiChatScreen(state)
    }
}