package com.br.kmpdemo.ui

import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel: ViewModel(), KoinComponent {
    protected val dispatcherProvider: DispatcherProvider by inject();
    fun launchIO(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launch(dispatcherProvider.IO, block = block)

}