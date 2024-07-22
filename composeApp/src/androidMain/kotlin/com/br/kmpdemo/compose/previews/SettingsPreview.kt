package com.br.kmpdemo.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.settings.SettingsScreen
import com.br.kmpdemo.compose.ui.settings.SettingsScreenState

@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview_Empty() {
    KmpDemoTheme {
        SettingsScreen(
            state = SettingsScreenState(
                isDarkMode = remember { mutableStateOf(false) },
                useCelsius = remember { mutableStateOf(false) },
                homeZipCodeText = remember { mutableStateOf("") },
                homeZipCodeErrorText = remember { mutableStateOf("") },
                onDarkModeChanged = {},
                onUseCelsiusChanged = {},
                onHomeZipCodeChanged = {},
                onSaveClicked = {}
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview_Populated() {
    KmpDemoTheme {
        SettingsScreen(
            state = SettingsScreenState(
                isDarkMode = remember { mutableStateOf(true) },
                useCelsius = remember { mutableStateOf(true) },
                homeZipCodeText = remember { mutableStateOf("90210") },
                homeZipCodeErrorText = remember { mutableStateOf("") },
                onDarkModeChanged = {},
                onUseCelsiusChanged = {},
                onHomeZipCodeChanged = {},
                onSaveClicked = {}
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview_Error() {
    KmpDemoTheme {
        SettingsScreen(
            state = SettingsScreenState(
                isDarkMode = remember { mutableStateOf(false) },
                useCelsius = remember { mutableStateOf(false) },
                homeZipCodeText = remember { mutableStateOf("abc") },
                homeZipCodeErrorText = remember { mutableStateOf("Invalid zip code") },
                onDarkModeChanged = {},
                onUseCelsiusChanged = {},
                onHomeZipCodeChanged = {},
                onSaveClicked = {}
            )
        )
    }
}