package com.br.kmpdemo.compose.previews

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.aichat.AiChatState
import com.br.kmpdemo.compose.ui.aichat.ChatMessage
import com.br.kmpdemo.compose.ui.aichat.AiChatScreen

@SuppressLint("UnrememberedMutableState")
@Preview(showSystemUi = true)
@Composable
fun ChatWindowPreview() {

    KmpDemoTheme {
        AiChatScreen(aiChatState)
    }
}

val aiChatState = AiChatState(
    messages = mutableStateOf(listOf(
        ChatMessage.UserMessage("Hello!"),
        ChatMessage.ModelMessage("How are you?"),
        ChatMessage.StatusMessage("Loading..."),
        ChatMessage.StatusMessage("Loading..."),
        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
//        ChatMessage.StatusMessage("Loading..."),
        ChatMessage.StatusMessage("Loading..."),
        ChatMessage.StatusMessage("Loading..."),
    ))
)