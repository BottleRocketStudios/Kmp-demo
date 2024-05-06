package com.br.kmpdemo.ui

import com.br.kmpdemo.resources.SharedRes
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel: BaseViewModel() {
    val appTitle = MutableStateFlow(SharedRes.strings.appName)
    val hideNavBar = MutableStateFlow(false)
}