
import androidx.compose.material3.ColorScheme
import com.br.kmpdemo.compose.resources.theme.Dimensions
import com.br.kmpdemo.utils.MeasurementType

expect fun getSystemDimensions(): Dimensions
expect fun getSystemColorScheme(darkTheme: Boolean): ColorScheme

expect object MeasurementPreference {
    var preference: MeasurementType
}