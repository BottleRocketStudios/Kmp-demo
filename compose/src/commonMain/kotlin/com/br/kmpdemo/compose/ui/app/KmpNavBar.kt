package com.br.kmpdemo.compose.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.ui.utils.NAV_ALT_BTN_SIZE
import com.br.kmpdemo.compose.ui.utils.NAV_FAB_BG_HEIGHT
import com.br.kmpdemo.compose.ui.utils.NAV_FAB_BG_WIDTH
import com.br.kmpdemo.compose.ui.utils.NAV_FAB_BTN_SIZE
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun KmpNavBar(
    onAddClick: () -> Unit = {},
    onMapPinClick: () -> Unit = {},
    onListClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.secondary),
        contentAlignment = Alignment.BottomCenter,
        content = {
            Image(
                painter = painterResource(SharedRes.images.nav_bar_background),
                contentDescription = stringResource(SharedRes.strings.nav_bar_bg),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.BottomCenter
            )
            Image(
                painter = painterResource(SharedRes.images.nav_bar_button_bg),
                contentDescription = stringResource(SharedRes.strings.nav_bar_button_bg),
                modifier = Modifier.width(NAV_FAB_BG_WIDTH.dp).height(NAV_FAB_BG_HEIGHT.dp),
                alignment = Alignment.BottomCenter
            )
            Image(
                painter = painterResource(SharedRes.images.nav_bar_button),
                contentDescription = stringResource(SharedRes.strings.nav_bar_button),
                modifier = Modifier
                    .size(NAV_FAB_BTN_SIZE.dp)
                    .clickable { onAddClick() },
                alignment = Alignment.Center
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        vertical = Dimens.grid_1_5,
                        horizontal = Dimens.grid_3,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    Image(
                        painter = painterResource(SharedRes.images.nav_bar_pin_button),
                        contentDescription = stringResource(SharedRes.strings.nav_bar_pin_button),
                        modifier = Modifier
                            .size(NAV_ALT_BTN_SIZE.dp)
                            .clickable { onMapPinClick() },
                        alignment = Alignment.CenterStart,
                    )
                    Image(
                        painter = painterResource(SharedRes.images.nav_bar_list_button),
                        contentDescription = stringResource(SharedRes.strings.nav_bar_list_button),
                        modifier = Modifier
                            .size(NAV_ALT_BTN_SIZE.dp)
                            .clickable { onListClick() },
                        alignment = Alignment.CenterEnd
                    )
                }
            )
        }
    )
}