import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.br.kmpdemo.compose.ui.app.KMPDemoApp


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP_example") {
    // FIXME - Connect app framework here.
    //        KMPDemoApp()
    }
}