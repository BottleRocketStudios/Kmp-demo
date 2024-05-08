package com.br.kmpdemo.viewmodels

import com.bottlerocketstudios.launchpad.utils.coroutine.DispatcherProvider
import com.bottlerocketstudios.launchpadutilsdomain.logger.Loggable
import com.bottlerocketstudios.launchpadutilsdomain.logger.LoggingManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel: ViewModel(), Loggable, KoinComponent {
    private val dispatcherProvider: DispatcherProvider by inject()
    fun launchIO(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launch(dispatcherProvider.IO, block = block)

    override val log: LoggingManager by inject()
}