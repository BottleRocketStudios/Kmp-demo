package com.br.wearapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import com.br.wearapp.presentation.mocks.MockData
import com.br.wearapp.presentation.theme.KMP_DemoTheme
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WearRootColumn(
    modifier: Modifier = Modifier,
    contentItems: List<Unit>,
    listState: ScalingLazyColumnState
) {
    ScalingLazyColumn(
        modifier = modifier,
        columnState = listState,
    ) {
        items(contentItems.size) {
            contentItems[it]
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun WearRootColumnPreview() {
    KMP_DemoTheme {
        WearRootColumn(
            modifier = Modifier,
            contentItems = listOf(CurrentTemp(state = MockData.getMockCurrentTempState())),
            listState = rememberResponsiveColumnState()
        )
    }
}