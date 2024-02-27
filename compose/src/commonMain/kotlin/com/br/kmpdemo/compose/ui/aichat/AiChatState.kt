package com.br.kmpdemo.compose.ui.aichat

data class AiChatState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val messages: List<ChatMessage> = emptyList(),
)

sealed class ChatMessage {
    data class UserMessage(val text: String) : ChatMessage()
    data class ModelMessage(val text: String) : ChatMessage()
    data class StatusMessage(val text: String) : ChatMessage()
}