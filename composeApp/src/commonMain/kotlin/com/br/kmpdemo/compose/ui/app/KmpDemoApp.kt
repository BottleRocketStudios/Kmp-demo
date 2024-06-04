package com.br.kmpdemo.compose.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.nav.mainNavGraph
import com.br.kmpdemo.compose.ui.MainWindowControlsImplementation
import com.br.kmpdemo.viewmodels.MainActivityViewModel

@Composable
fun KMPDemoApp(
    widthSize: WindowWidthSizeClass,
    navController: NavHostController,
    devicePosture: DevicePosture,
    bottomBar: @Composable () -> Unit,
) {
    val activityViewModel = viewModel { MainActivityViewModel() }
    val windowControls by lazy { MainWindowControlsImplementation(activityViewModel) }

    Scaffold(
        topBar = {},
        bottomBar = {
//            TODO - don't show this if keyboard
            if (!windowControls.hideNavBar) bottomBar()
        },
    ) { it: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
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