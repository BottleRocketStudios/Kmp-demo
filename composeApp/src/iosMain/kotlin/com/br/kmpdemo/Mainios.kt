package com.br.kmpdemo

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.bottlerocketstudios.launchpad.compose.navigation.NavigationWrapper
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.br.kmpdemo.compose.nav.NavRoutes
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.app.KMPDemoApp
import com.br.kmpdemo.compose.ui.app.KmpNavBar
import com.br.kmpdemo.compose.ui.app.kmpDemoAppNavItems
import com.br.kmpdemo.di.appModule
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCValues
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavOptions
import org.koin.core.context.startKoin
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIApplicationMain
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIScreen
import platform.UIKit.UIWindow

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun mainIos() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
fun UIWindow.getWindowWidthSizeClass() =
    when (bounds.size) {
        in 840..Int.MAX_VALUE -> WindowWidthSizeClass.Expanded
        in 600..840 -> WindowWidthSizeClass.Medium
        in Int.MIN_VALUE..600 -> WindowWidthSizeClass.Compact
        else -> WindowWidthSizeClass.Medium
    }

@OptIn(BetaInteropApi::class)
class SkikoAppDelegate @OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    @OptIn(ExperimentalForeignApi::class)
    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        startKoin {
            modules(appModule())
            allowOverride(false)
        }

        window = UIWindow(frame = UIScreen.mainScreen.bounds).apply {
            rootViewController =  ComposeUIViewController {
                PreComposeApp {
                    KmpDemoTheme {
                        NavigationWrapper(
                            widthSize = getWindowWidthSizeClass(),
                            devicePosture = DevicePosture.NormalPosture,
                            navigationItems = kmpDemoAppNavItems,
                        ) { navigator, _ ->
                            KMPDemoApp(
                                widthSize = getWindowWidthSizeClass(),
                                navigator = navigator,
                                devicePosture = DevicePosture.NormalPosture,
                                bottomBar = {
                                    KmpNavBar(
                                        onAddClick = {
                                            navigator?.navigate(
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
            makeKeyAndVisible()
        }
        return true
    }
}