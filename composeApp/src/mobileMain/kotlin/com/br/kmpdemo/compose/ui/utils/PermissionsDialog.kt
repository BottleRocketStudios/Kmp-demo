package com.br.kmpdemo.compose.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme.dimens
import com.br.kmpdemo.compose.resources.theme.bold
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch


@Composable
fun PermissionsDialog(
    permissionText: String,
    onDismiss: (Boolean) -> Unit,
    permissionsController: PermissionsController,
    permissionTypes: List<Permission>
) {
    val scope = rememberCoroutineScope()

    BindEffect(permissionsController)

    AlertDialog(
        onDismissRequest = { onDismiss(false) },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider()
                Text(
                    text = stringResource(SharedRes.strings.grant_permission),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.bold(),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                permissionTypes.forEach { permission ->
                                    permissionsController.providePermission(permission)
                                }
                                onDismiss(
                                    permissionsController.isPermissionGranted(
                                        Permission.LOCATION
                                    ) || permissionsController.isPermissionGranted(
                                        Permission.COARSE_LOCATION
                                    )
                                )
                            }
                        }
                        .padding(dimens.grid_2)
                )
            }
        },
        title = {
            Text(
                text = stringResource(SharedRes.strings.permission_required),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = permissionText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}