package com.br.kmpdemo.compose.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.nav.mainNavGraph
import com.br.kmpdemo.compose.ui.MainWindowControlsImplementation
import com.br.kmpdemo.viewmodels.MainActivityViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun KMPDemoApp(
    widthSize: WindowWidthSizeClass,
    navigator: Navigator?,
    devicePosture: DevicePosture,
    bottomBar: @Composable () -> Unit,
) {
    val activityViewModel = viewModel(modelClass = MainActivityViewModel::class) {
        MainActivityViewModel()
    }
    val windowControls by lazy { MainWindowControlsImplementation(activityViewModel) }

    Scaffold(
        topBar = {},
        bottomBar = {
//            TODO - don't show this if keyboard
            if (!windowControls.hideNavBar && navigator != null) bottomBar()
        },
    ) { it: PaddingValues ->
        navigator?.let { navController ->
            NavHost(
                // Assign the navigator to the NavHost
                navigator = navController,
                // Navigation transition for the scenes in this NavHost, this is optional
                navTransition = NavTransition(),
                // The start destination
                initialRoute = NavRoutes.HOME,
                modifier = Modifier.padding(it)
            ) {
                mainNavGraph(
                    navigator = navController,
                    windowControls = windowControls,
                    widthSizeClass = widthSize,
                    devicePosture = devicePosture
                )
            }
        }
    }
}