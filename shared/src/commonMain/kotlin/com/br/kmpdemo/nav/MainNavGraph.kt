package com.br.kmpdemo.nav

import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.ui.MainWindowControls
import com.br.kmpdemo.ui.aichat.aiChatComposable
import com.br.kmpdemo.ui.home.homeComposable
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

fun RouteBuilder.mainNavGraph(
    navigator: Navigator,
    windowControls: MainWindowControls?,
    widthSizeClass: WindowWidthSizeClass,
    devicePosture: DevicePosture,
) {
    homeComposable(navigator)
    aiChatComposable(navigator)
}
