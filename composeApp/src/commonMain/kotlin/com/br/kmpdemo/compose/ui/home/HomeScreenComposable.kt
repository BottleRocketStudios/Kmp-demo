package com.br.kmpdemo.compose.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.viewmodels.HomeViewModel

fun NavGraphBuilder.homeComposable(navigator: NavController) {
    composable(route = NavRoutes.HOME) {
        val homeViewModel = viewModel { HomeViewModel() }

        val state: HomeState = homeViewModel.toState()

        HomeBottomSheetScaffold(state)
    }
}