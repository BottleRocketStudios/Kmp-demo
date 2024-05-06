package com.br.kmpdemo.compose.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.previews.utils.MockData
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KmpNavBar
import com.br.kmpdemo.compose.ui.home.HomeBottomSheet
import com.br.kmpdemo.compose.ui.home.HomeCurrentWeather
import com.br.kmpdemo.compose.ui.home.HomeScreen

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    KmpDemoTheme {
        HomeScreen(MockData.getMockHomeState(), true)
    }
}

@Preview
@Composable
private fun BottomSheetPreview() {
    KmpDemoTheme {
        HomeBottomSheet(state = MockData.getMockHomeState(), true)
    }
}

@Composable
@Preview
fun NavBarPreview() {
    KmpDemoTheme {
        KmpNavBar()
    }
}

@Composable
@Preview
fun HomeCurrentWeatherPreview() {
    KmpDemoTheme {
        Surface {
            HomeCurrentWeather(MockData.getMockHomeState(), true)
        }
    }
}

@Composable
@Preview
fun HomeCurrentWeatherERRORPreview() {
    KmpDemoTheme {
        Surface {
            HomeCurrentWeather(MockData.getMockHomeState(isError = true), true)
        }
    }
}