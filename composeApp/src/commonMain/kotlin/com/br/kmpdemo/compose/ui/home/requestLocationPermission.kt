package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.Composable

@Composable
expect fun requestLocationPermission(
    permissionText: String,
    onDismiss: (Boolean) -> Unit
)