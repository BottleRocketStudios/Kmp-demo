package com.br.kmpdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.heatmaps.WeightedLatLng

@Composable
actual fun WeatherMapScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        var uiSettings by remember { mutableStateOf(MapUiSettings()) }
        var properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
        }
//        var tileState by rememberTileOverlayState {
//            pos
//        }

        val data = WeightedLatLng(LatLng(32.949480, -96.823970), 2.0)
        val weightedLatLngs = ArrayList<WeightedLatLng>()
        weightedLatLngs.add(data)

        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = properties,
                uiSettings = uiSettings,
                onMapLoaded = {
//                            TileOverlay(
//                                tileProvider = HeatmapTileProvider.Builder().weightedData(weightedLatLngs).build(),
//                                state = tileState,
//                                fadeIn = true,
//                                transparency = 0f,
//                                visible = true,
//                                zIndex = 0f,
//                                onClick = {},
//                            )
                }
            )
            Switch(
                checked = uiSettings.myLocationButtonEnabled,
                onCheckedChange = {
                    uiSettings = uiSettings.copy(myLocationButtonEnabled = it)
                }
            )
        }
    }
}

@Preview
@Composable
private fun WeatherHomePreview() {
    WeatherMapScreen()
}