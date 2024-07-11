package com.br.wearapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.tooling.preview.devices.WearDevices
import com.br.wearapp.presentation.theme.KMP_DemoTheme
import com.br.wearapp.presentation.ui.WearApp
import com.br.wearapp.presentation.utils.getScreenList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            KMP_DemoTheme {
                WearApp(getScreenList())
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun AppPreview() {
    KMP_DemoTheme {
        WearApp(getScreenList())
    }
}