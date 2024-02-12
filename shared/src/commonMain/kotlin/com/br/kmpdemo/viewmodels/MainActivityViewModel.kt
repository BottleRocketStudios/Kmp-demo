package com.br.kmpdemo.viewmodels

import BaseViewModel
import com.br.kmpdemo.compose.resources.SharedRes
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel: BaseViewModel() {
    val appTitle = MutableStateFlow(SharedRes.strings.appName)
    val hideNavBar = MutableStateFlow(false)
}