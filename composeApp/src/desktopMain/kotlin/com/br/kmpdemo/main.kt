import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.singleWindowApplication
import com.bottlerocketstudios.launchpad.compose.navigation.NavigationWrapper
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KMPDemoApp
import com.br.kmpdemo.compose.ui.app.KmpNavBar
import com.br.kmpdemo.compose.ui.app.kmpDemoAppNavItems
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.rememberNavigator


// TODO - Consider moving this into LaunchPad Compose
fun WindowState.getWindowWidthSizeClass() =
    when (size.width.value.toLong()) {
        in 840..Int.MAX_VALUE -> WindowWidthSizeClass.Expanded
        in 600..840 -> WindowWidthSizeClass.Medium
        in Int.MIN_VALUE..600 -> WindowWidthSizeClass.Compact
        else -> WindowWidthSizeClass.Medium
    }

fun main() = application {
    val windowState = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Kmp Weather",
        state = windowState,
        alwaysOnTop = true,
        focusable = true,
        visible = true,
    ) {
        PreComposeApp {
            val navigator = rememberNavigator()
            KmpDemoTheme {
                KMPDemoApp(
                    widthSize = windowState.getWindowWidthSizeClass(),
                    navigator = navigator,
                    devicePosture = DevicePosture.NormalPosture,
                    bottomBar = {
                        KmpNavBar(
                            onAddClick = {
                                navigator.navigate(
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