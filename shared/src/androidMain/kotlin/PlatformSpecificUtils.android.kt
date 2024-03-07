import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material3.ColorScheme
import com.br.kmpdemo.compose.resources.theme.Dimensions
import com.br.kmpdemo.compose.resources.theme.kmpDarkColors
import com.br.kmpdemo.compose.resources.theme.kmpLightColors
import com.br.kmpdemo.compose.resources.theme.sw360Dimensions
import com.br.kmpdemo.utils.Constants.IS_METRIC
import com.br.kmpdemo.utils.Constants.MEASUREMENT_PREFS
import com.br.kmpdemo.utils.MeasurementType

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
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(MEASUREMENT_PREFS, Context.MODE_PRIVATE)
    }

    actual var preference: MeasurementType
        get() = if (preferences.getBoolean(IS_METRIC, false)) MeasurementType.IMPERIAL else MeasurementType.METRIC
        set(value) {
            preferences.edit().putBoolean(IS_METRIC, value == MeasurementType.METRIC).apply()
        }
}