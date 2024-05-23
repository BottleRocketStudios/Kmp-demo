package com.br.kmpdemo.compose.nav

import com.br.kmpdemo.compose.ui.MainWindowControls
import com.br.kmpdemo.compose.ui.aichat.aiChatComposable
import com.br.kmpdemo.compose.ui.home.homeComposable
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass

fun RouteBuilder.mainNavGraph(
    navigator: Navigator,
    windowControls: MainWindowControls?,
    widthSizeClass: WindowWidthSizeClass,
    devicePosture: DevicePosture,
) {
    homeComposable(navigator)
    aiChatComposable(navigator)
}
