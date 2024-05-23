package com.br.kmpdemo.viewmodels

import com.br.kmplaunchpadai.domain.mediator.GeminiMediator
import com.br.kmplaunchpadai.domain.mediator.GeminiParametersType
import com.br.kmplaunchpadai.domain.mediator.GeminiResponseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf


object MyFunctions {

    fun getWeather(args: GeminiParametersType): GeminiResponseType =
        Json.encodeToJsonElement<Map<String, String>>(
            mapOf(
                "forecast" to "Rainy",
                "temperature" to "70f"
            )
        )
}


class AiChatViewModel: BaseViewModel() {
    //region DI
//    TODO - Add this as build config or something.
    private val geminiMediator: GeminiMediator by inject {
        parametersOf(viewModelScope, "AIzaSyCiul-P41STf7v8thUYzm-CGZPvLjkr0YU")
    }
    //endregion

    init {
        geminiMediator {
            functionDeclaration {
                name { "getWeather" }
                description { "Gets the weather for the next 24 hours" }
                functionReference { MyFunctions::getWeather }
            }
        }
    }

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
        launchIO {
            geminiMediator.user.emit(input)
        }
    }
    //endregion

}