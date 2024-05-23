import com.br.kmpdemo.utils.MeasurementType
import org.koin.core.component.KoinComponent

actual class KmpLocationProvider actual constructor() : KoinComponent {
    actual suspend fun getUsersLocation() {
    }
}

actual object MeasurementPreference {
    actual var preference: MeasurementType = MeasurementType.IMPERIAL
}