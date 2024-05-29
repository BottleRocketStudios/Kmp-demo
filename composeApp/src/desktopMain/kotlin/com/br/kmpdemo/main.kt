package com.br.kmpdemo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP_example") {
    // FIXME - Connect app framework here.
    //        KMPDemoApp()
    }
}