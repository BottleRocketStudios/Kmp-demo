package com.br.wearapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.br.kmpdemo.utils.WeatherUtils.asDisplayUnit
import com.br.kmpdemo.utils.WeatherUtils.roundToDisplayDouble
import com.br.wearapp.R
import com.br.wearapp.presentation.mocks.MockData
import com.br.wearapp.presentation.theme.KMP_DemoTheme

@Composable
fun CurrentTemp(
    state: CurrentTempState,
    modifier: Modifier = Modifier
) {
    with(state) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = temperature.value?.let { temp ->
                    stringResource(
                        R.string.input_degrees_with_unit,
                        temp.roundToDisplayDouble(),
                        state.measurementPref.value.asDisplayUnit(),
                    )
                } ?: stringResource(R.string.tempError),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.display1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun HomeScreenSmallPreview() {
    KMP_DemoTheme {
        CurrentTemp(MockData.getMockCurrentTempState())
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun HomeScreenLargePreview() {
    KMP_DemoTheme {
        CurrentTemp(MockData.getMockCurrentTempState())
    }
}