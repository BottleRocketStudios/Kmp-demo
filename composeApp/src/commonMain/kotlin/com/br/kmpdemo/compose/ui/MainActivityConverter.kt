package com.br.kmpdemo.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.compose.ui.app.KmpDemoAppState
import com.br.kmpdemo.viewmodels.MainActivityViewModel

@Composable
fun MainActivityViewModel.toState() = KmpDemoAppState(
    appTitle = appTitle.collectAsState().value,
    hideNavBar = hideNavBar.collectAsState().value,
)