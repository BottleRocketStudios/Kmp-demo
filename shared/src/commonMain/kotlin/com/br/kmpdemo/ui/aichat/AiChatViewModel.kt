package com.br.kmpdemo.ui.aichat

import com.br.kmpdemo.ui.BaseViewModel
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AiChatViewModel: BaseViewModel() {
    //region DI
    private val geminiMediator: GeminiMediator by inject {
        parametersOf(viewModelScope)
    }
    //endregion

    //region UI
    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")
    val messages = geminiMediator.conversation
    //endregion

    init {
        launchIO {
            geminiMediator.startChat()
        }
    }

    //region: Callbacks
    fun userInput(input: String) {
        geminiMediator.user.value = input
    }
    //endregion

}