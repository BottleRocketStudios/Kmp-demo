package com.br.kmpdemo.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel: BaseViewModel() {
    val appTitle = MutableStateFlow("KMP Weather")
    val hideNavBar = MutableStateFlow(false)
}