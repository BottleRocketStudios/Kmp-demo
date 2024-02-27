package com.br.kmpdemo.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.ui.aichat.AiChatState
import com.br.kmpdemo.compose.ui.aichat.ChatMessage
import com.br.kmpdemo.compose.ui.aichat.ChatWindow

@Preview(showSystemUi = true)
@Composable
fun ChatWindowPreview() {
    val aiChatState = AiChatState(
        messages = listOf(
            ChatMessage.UserMessage("Hello!"),
            ChatMessage.ModelMessage("How are you?"),
            ChatMessage.StatusMessage("Loading...")
        )
    )

    ChatWindow(aiChatState)
}