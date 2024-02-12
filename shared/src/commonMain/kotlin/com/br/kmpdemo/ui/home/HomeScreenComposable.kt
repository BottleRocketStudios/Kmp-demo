package com.br.kmpdemo.ui.home

import com.br.kmpdemo.NavRoutes
import com.br.kmpdemo.compose.ui.home.HomeBottomSheetScaffold
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.viewmodels.HomeViewModel
import com.br.kmpdemo.viewmodels.converters.toState
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel

fun RouteBuilder.homeComposable(navigator: Navigator) {
    scene(route = NavRoutes.HOME, navTransition = NavTransition()) {
        val homeViewModel = viewModel(HomeViewModel::class) {
            HomeViewModel()
        }

        val state: HomeState = homeViewModel.toState()

        HomeBottomSheetScaffold(state)
    }
}