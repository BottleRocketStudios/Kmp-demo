package com.br.kmpdemo.compose.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavOptions

@Composable
fun AppWrapper() {
    PreComposeApp {
        KmpDemoTheme {
            NavigationWrapper(
                widthSize = getWindowWidthSize(this@MainActivity),
                devicePosture = devicePostureFlow.collectAsState().value,
                navigationItems = kmpDemoAppNavItems,
            ) { navigator, _ ->
                KMPDemoApp(
                    widthSize = getWindowWidthSize(this),
                    navigator = navigator,
                    devicePosture = devicePostureFlow.collectAsState().value,
                    bottomBar = {
                        KmpNavBar(
                            onAddClick = {
                                navigator?.navigate(
                                    route = NavRoutes.AICHAT,
                                    options = NavOptions(launchSingleTop = true)
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
