package com.br.kmpdemo.compose.ui.weatherDetails.airQuality

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.bold
import com.br.kmpdemo.compose.resources.theme.letterSpacing
import com.br.kmpdemo.compose.ui.shared.KMMDemoSlider
import com.br.kmpdemo.compose.ui.weatherDetails.DETAILS_WIDGET_SIZE
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AirQualityWidget(risk: AirQualityEnum = AirQualityEnum.UNKNOWN) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(DETAILS_WIDGET_SIZE),
        color = Colors.primaryContainer,
        shape = RoundedCornerShape(Dimens.grid_3),
        border = BorderStroke(
            width = 1.dp,
            color = Colors.outlineVariant,
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.grid_2_5)
        ) {

            DetailsWidgetLabel(
                icon = SharedRes.images.air_quality,
                iconDesc = SharedRes.strings.air_quality_icon,
                label = SharedRes.strings.air_quality)

            Text(
                stringResource(risk.quality),
                modifier = Modifier.padding(top = Dimens.grid_1_75),
                style = MaterialTheme.typography.bodyLarge.bold().letterSpacing(0.86.sp)
            )

            KMMDemoSlider(initValue = risk.level, valueRange = 0f..0.6f)

            Divider(
                thickness = 1.dp,
                color = Colors.onTertiary.copy(alpha = 0.1f)
            )

            Row(
                modifier = Modifier
                    .padding(top = Dimens.grid_2)
                    .fillMaxWidth()
                    .clickable(onClick = {
                        // TODO: Add `See More` destination
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                content = {
                    Text(
                        stringResource(SharedRes.strings.see_more),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Icon(
                        painter = painterResource(SharedRes.images.chevron_right),
                        contentDescription = stringResource(SharedRes.strings.chevron_right),
                    )
                }
            )
        }
    }
}