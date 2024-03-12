package com.br.kmpdemo.compose.ui.aichat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class AiChatState(
    val isLoading: State<Boolean> = mutableStateOf(false),
    val errorMessage: State<String> = mutableStateOf(""),
    val messages: State<List<ChatMessage>> = mutableStateOf(emptyList()),
    val userInput: (String) -> Unit = {},
)

sealed class ChatMessage {
    data class UserMessage(val text: String) : ChatMessage()
    data class ModelMessage(val text: String) : ChatMessage()
    data class StatusMessage(val text: String) : ChatMessage()
}