package com.br.kmpdemo.ui.aichat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.compose.ui.aichat.AiChatState
import com.br.kmpdemo.compose.ui.aichat.ChatMessage
import com.br.kmplaunchpadai.domain.mediator.GeminiContent
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator.Companion.MODEL
import com.br.kmplaunchpadai.domain.mediator.GeminiMediator.Companion.USER
import kotlinx.coroutines.flow.map

@Composable
fun AiChatViewModel.toState() = AiChatState(
    isLoading = isLoading.collectAsState(),
    errorMessage = errorMessage.collectAsState(),
    messages = messages.map { messages ->
        messages.mapNotNull { it.toChatMessage() }
    }.collectAsState(emptyList()),
) {}


fun GeminiContent.toChatMessage(): ChatMessage? = when  {
    part?.text.isNullOrEmpty() -> null
    role == USER -> ChatMessage.UserMessage(part?.text ?: "")
    role == MODEL -> ChatMessage.ModelMessage(part?.text ?: "")
//    TODO - Status Message
    else -> null
}