package com.br.kmpdemo.compose.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.br.kmpdemo.compose.ui.MainWindowControls
import com.br.kmpdemo.compose.ui.aichat.aiChatComposable
import com.br.kmpdemo.compose.ui.home.homeComposable
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass

fun NavGraphBuilder.mainNavGraph(
    navigator: NavController,
    windowControls: MainWindowControls?,
    widthSizeClass: WindowWidthSizeClass,
    devicePosture: DevicePosture,
) {
    homeComposable(navigator)
    aiChatComposable(navigator)
}
