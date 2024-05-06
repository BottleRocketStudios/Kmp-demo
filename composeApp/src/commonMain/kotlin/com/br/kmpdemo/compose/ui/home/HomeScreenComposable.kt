package com.br.kmpdemo.compose.ui.home

import com.br.kmpdemo.nav.NavRoutes
import com.br.kmpdemo.ui.home.HomeViewModel
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