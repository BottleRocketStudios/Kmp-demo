package com.br.kmpdemo.compose.ui.weatherDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.letterSpacing
import com.br.kmpdemo.compose.resources.theme.semiBold
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DetailsWidgetLabel(
    icon: ImageResource,
    iconSize: Dp = Dimens.grid_2_25,
    iconDesc: StringResource,
    label: StringResource,
    spacedBy: Dp = Dimens.grid_0_75
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacedBy, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            tint = Colors.onTertiary,
            painter = painterResource(icon),
            contentDescription = stringResource(iconDesc),
        )
        Text(
            stringResource(label),
            color = Colors.onSecondary,
            style = MaterialTheme.typography.labelLarge.semiBold().letterSpacing(0.86.sp)
        )
    }
}