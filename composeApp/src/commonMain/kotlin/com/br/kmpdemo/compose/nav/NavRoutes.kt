package com.br.kmpdemo.compose.nav

import kotlinx.serialization.Serializable

// TODO - Port to type safe nav when CM lib gets updated to handle it.
// Destination names may need to change
object NavRoutes {
    const val HOME = "/home"
    const val AICHAT = "/aichat"
}

object Routes {
    @Serializable
    object Home
    @Serializable
    object AIChat
}
