package com.br.kmpdemo.compose.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bottlerocketstudios.launchpad.compose.navigation.NavigationWrapper
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.nav.Routes
import com.br.kmpdemo.compose.nav.mainNavGraph
import com.br.kmpdemo.compose.ui.MainWindowControlsImplementation
import com.br.kmpdemo.viewmodels.MainActivityViewModel

@Composable
fun KMPDemoAppOld(
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

val navItems = listOf(
    NavigationItem(
        route = NavRoutes.HOME,
        selectedIcon = { Icon(Icons.Filled.Home, contentDescription = "HOME") },
        unselectedIcon = { Icon(Icons.Outlined.Home, contentDescription = "HOME") },
        label = { Text("Home") },
    ),
    NavigationItem(
        route = NavRoutes.AICHAT,
        selectedIcon = { Icon(Icons.Filled.Call, contentDescription = "AI CHAT") },
        unselectedIcon = { Icon(Icons.Outlined.Call, contentDescription = "AI CHAT") },
        label = { Text("AI Chat") },
    )

)

@Composable
fun KMPDemoApp(
    widthSize: WindowWidthSizeClass,
    devicePosture: DevicePosture,
) {
    val activityViewModel = viewModel { MainActivityViewModel() }
    val windowControls by lazy { MainWindowControlsImplementation(activityViewModel) }

    NavigationWrapper(
        widthSize = widthSize,
        devicePosture = devicePosture,
        navigationItems = navItems,
    ) { navController: NavHostController ->
        Scaffold(
            topBar = {},
        ) {
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
}