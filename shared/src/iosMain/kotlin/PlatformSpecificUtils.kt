
import androidx.compose.material3.ColorScheme
import com.br.kmpdemo.compose.resources.theme.Dimensions
import com.br.kmpdemo.compose.resources.theme.kmpDarkColors
import com.br.kmpdemo.compose.resources.theme.kmpLightColors
import com.br.kmpdemo.compose.resources.theme.sw360Dimensions
import com.br.kmpdemo.utils.Constants.IS_METRIC
import com.br.kmpdemo.utils.MeasurementType
import platform.Foundation.NSUserDefaults

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

actual object MeasurementPreference {
    private val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults

    actual var preference: MeasurementType
        get() = if (userDefaults.boolForKey(IS_METRIC)) MeasurementType.METRIC else MeasurementType.IMPERIAL
        set(value) {
            userDefaults.setBool(value == MeasurementType.METRIC, IS_METRIC)
            userDefaults.synchronize()
        }
}