package com.br.kmpdemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.ui.app.KmpDemoAppState

@Composable
fun MainActivityViewModel.toState() = KmpDemoAppState(
    appTitle = appTitle.collectAsState().value,
    hideNavBar = hideNavBar.collectAsState().value,
)