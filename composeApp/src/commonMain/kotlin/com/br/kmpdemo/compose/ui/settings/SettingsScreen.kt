package com.br.kmpdemo.compose.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.theme.Dimens


@Composable
fun SettingsScreen(state: SettingsScreenState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.grid_1_5),
        verticalArrangement = Arrangement.spacedBy(Dimens.grid_1_5)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.grid_0_5)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.grid_1_5)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.grid_0_5),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = state.isDarkMode.value,
                        onCheckedChange = state.onDarkModeChanged
                    )
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.grid_0_5),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Use Celsius",
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = state.useCelsius.value,
                        onCheckedChange = state.onUseCelsiusChanged
                    )
                }
            }
        }
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.grid_0_5)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.grid_1_5)
            ) {
                OutlinedTextField(
                    value = state.homeZipCodeText.value,
                    onValueChange = state.onHomeZipCodeChanged,
                    label = { Text("Home Zip Code") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.homeZipCodeErrorText.value.isNotEmpty()
                )
                if (state.homeZipCodeErrorText.value.isNotEmpty()) {
                    Text(
                        text = state.homeZipCodeErrorText.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = Dimens.grid_1)
                    )
                }
            }
        }
        Button(
            onClick = state.onSaveClicked,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}

data class SettingsScreenState(
    val isDarkMode: State<Boolean>,
    val useCelsius: State<Boolean>,
    val homeZipCodeText: State<String>,
    val homeZipCodeErrorText: State<String>,
    val onDarkModeChanged: (Boolean) -> Unit,
    val onUseCelsiusChanged: (Boolean) -> Unit,
    val onHomeZipCodeChanged: (String) -> Unit,
    val onSaveClicked: () -> Unit
)