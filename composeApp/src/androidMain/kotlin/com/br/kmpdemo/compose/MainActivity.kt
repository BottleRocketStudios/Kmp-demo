package com.br.kmpdemo.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bottlerocketstudios.launchpad.compose.navigation.util.createDevicePostureFlow
import com.bottlerocketstudios.launchpad.compose.navigation.util.getWindowWidthSize
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.br.kmpdemo.MokoLocationProvider
import com.br.kmpdemo.bind
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KMPDemoApp
import com.br.kmpdemo.compose.ui.app.KmpNavBar
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : FragmentActivity(), KoinComponent {
    private val devicePostureFlow: StateFlow<DevicePosture> = createDevicePostureFlow()
    private val locationProvider: MokoLocationProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationProvider.bind(lifecycle, this, supportFragmentManager)
        setContent {
            val navController: NavHostController = rememberNavController()
            KmpDemoTheme {
                KMPDemoApp(
                    widthSize = getWindowWidthSize(this),
                    navController = navController,
                    devicePosture = devicePostureFlow.collectAsState().value,
                    bottomBar = {
                        KmpNavBar(
                            onAddClick = {
                                navController.navigate(NavRoutes.AICHAT) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}