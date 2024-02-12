package com.br.kmpdemo.compose.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HomeScreen(state: HomeState, isExpanded: Boolean) {
    Surface(
        color = Colors.secondary,
        modifier = Modifier.fillMaxSize(),
        content = {
            AnimatedVisibility(
                visible = !isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Image(
                    painter = painterResource(SharedRes.images.night_landscape),
                    contentDescription = stringResource(SharedRes.strings.home_bg),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth,
                )
            }
            HomeCurrentWeather(state, isExpanded)
        }
    )
}