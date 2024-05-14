package com.br.kmpdemo.compose.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.br.kmpdemo.compose.ui.utils.PermissionsDialog
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
actual fun requestLocationPermission(
    permissionText: String,
    onDismiss: (Boolean) -> Unit
) {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController =
        remember(factory) { factory.createPermissionsController() }
    var locationPermissionGranted by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        locationPermissionGranted = controller.isPermissionGranted(Permission.LOCATION) ||
                controller.isPermissionGranted(Permission.COARSE_LOCATION)

        // If location permissions were previously granted, onDismiss function is called that
        // will get users current location
        if (locationPermissionGranted) {
            onDismiss(true)
        }
    }

    // IF location permissions have not been granted, start the permissions flow
    AnimatedVisibility(!locationPermissionGranted) {
        PermissionsDialog(
            permissionText = permissionText,
            onDismiss = onDismiss,
            permissionsController = controller,
            permissionTypes = listOf(Permission.LOCATION, Permission.COARSE_LOCATION)
        )
    }

}