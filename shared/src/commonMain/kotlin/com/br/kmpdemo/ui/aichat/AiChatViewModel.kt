package com.br.kmpdemo.ui.aichat

import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.BaseViewModel
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.viewModel
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
    val messages = geminiMediator.conversationFlow
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