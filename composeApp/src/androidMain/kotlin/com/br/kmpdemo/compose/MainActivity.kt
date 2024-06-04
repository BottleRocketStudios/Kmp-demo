package com.br.kmpdemo.compose

import com.br.kmpdemo.KmpLocationProvider
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.FragmentActivity
import com.bottlerocketstudios.launchpad.compose.navigation.NavigationWrapper
import com.bottlerocketstudios.launchpad.compose.navigation.util.createDevicePostureFlow
import com.bottlerocketstudios.launchpad.compose.navigation.util.getWindowWidthSize
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KMPDemoApp
import com.br.kmpdemo.compose.ui.app.KmpNavBar
import com.br.kmpdemo.compose.ui.app.kmpDemoAppNavItems
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : FragmentActivity(), KoinComponent {
    private val devicePostureFlow: StateFlow<DevicePosture> = createDevicePostureFlow()
    private val locationProvider: KmpLocationProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationProvider.init()
        setContent {
            PreComposeApp {
                val navigator = rememberNavigator()
                KmpDemoTheme {
                    KMPDemoApp(
                        widthSize = getWindowWidthSize(this),
                        navigator = navigator,
                        devicePosture = devicePostureFlow.collectAsState().value,
                        bottomBar = {
                            KmpNavBar(
                                onAddClick = {
                                    navigator.navigate(
                                        route = NavRoutes.AICHAT,
                                        options = NavOptions(launchSingleTop = true)
                                    )
                                }
                            )
                        }
                    )
                }
            }
        }
    }

}