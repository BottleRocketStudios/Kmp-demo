package com.br.kmpdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.ui.app.KmpDemoAppState
import com.br.kmpdemo.viewmodels.MainActivityViewModel

@Composable
fun MainActivityViewModel.toState() = KmpDemoAppState(
    appTitle = appTitle.collectAsState().value,
    hideNavBar = hideNavBar.collectAsState().value,
)