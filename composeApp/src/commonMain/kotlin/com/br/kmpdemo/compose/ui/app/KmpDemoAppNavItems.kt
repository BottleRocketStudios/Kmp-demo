package com.br.kmpdemo.compose.ui.app

import androidx.compose.material3.Text
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.br.kmpdemo.compose.nav.NavRoutes

val kmpDemoAppNavItems = listOf<NavigationItem>(
    NavigationItem(
        route = NavRoutes.HOME,
        selectedIcon = {},
        unselectedIcon = {},
        label = { Text("Home") },
        onClick = {},
    ),
    NavigationItem(
        route = NavRoutes.MAP,
        selectedIcon = {},
        unselectedIcon = {},
        label = { Text("Location") },
        onClick = {},
    ),
    NavigationItem(
        route = NavRoutes.ADD,
        selectedIcon = {},
        unselectedIcon = {},
        label = { Text("Add") },
        onClick = {},
    ),
    NavigationItem(
        route = NavRoutes.LIST,
        selectedIcon = {},
        unselectedIcon = {},
        label = { Text("List") },
        onClick = {},
    )
)
