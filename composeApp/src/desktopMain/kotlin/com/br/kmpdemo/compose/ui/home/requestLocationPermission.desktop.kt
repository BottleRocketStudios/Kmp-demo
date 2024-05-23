package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.Composable

@Composable
actual fun requestLocationPermission(
    permissionText: String,
    onDismiss: (Boolean) -> Unit
) {
}