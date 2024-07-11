
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.util.getWindowWidthSizeClass
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KMPDemoApp
import com.br.kmpdemo.di.appModule
import org.koin.core.context.startKoin


fun main() {
    startKoin {
        modules(appModule())
        allowOverride(false)
    }

    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            title = "Kmp Weather",
            state = windowState,
            alwaysOnTop = true,
            focusable = true,
            visible = true,
        ) {
            KmpDemoTheme {
                KMPDemoApp(
                    widthSize = windowState.getWindowWidthSizeClass(),
                    devicePosture = DevicePosture.NormalPosture,
                )
            }
        }
    }
}