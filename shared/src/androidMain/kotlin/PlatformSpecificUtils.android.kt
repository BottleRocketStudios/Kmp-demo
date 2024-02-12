import androidx.compose.material3.ColorScheme
import com.br.kmpdemo.compose.resources.theme.kmpDarkColors
import com.br.kmpdemo.compose.resources.theme.kmpLightColors

actual fun getSystemDimensions(): Dimensions {
    // Return iOS-specific dimensions
    return sw360Dimensions
}

actual fun getSystemColorScheme(darkTheme: Boolean): ColorScheme {
    // Return iOS-specific color scheme
    return if (darkTheme) {
        kmpDarkColors
    } else {
        kmpLightColors
    }
}