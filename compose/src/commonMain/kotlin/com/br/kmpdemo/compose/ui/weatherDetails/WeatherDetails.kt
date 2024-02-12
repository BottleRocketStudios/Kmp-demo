
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsRow
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsState
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityWidget
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityWidget
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallWidget
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetWidget
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UvIndexWidget
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindWidget

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherDetails(state: WeatherDetailsState) {
    with(state) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = Dimens.grid_1_25,
                    start = Dimens.grid_2_5,
                    end = Dimens.grid_2_5,
                ),
        ) {
            AirQualityWidget(airQuality)

            WeatherDetailsRow {
                UvIndexWidget(uvIndexState)
                SunriseSunsetWidget(sunriseSunsetState)
            }

            WeatherDetailsRow {
                WindWidget(windState)
                RainFallWidget(rainFallState)
            }

            WeatherDetailsRow {
                FeelsLikeWidget(feelsLikeState)
                HumidityWidget(humidityState)
            }

            WeatherDetailsRow {
                VisibilityWidget(visibilityState)
                BarometricPressureWidget(pressureState)
            }
        }
    }
}