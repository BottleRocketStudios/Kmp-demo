package com.br.kmpdemo.compose.ui.aichat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.br.kmpdemo.compose.resources.theme.Dimens

@Composable
fun ChatWindow(
    aiChatState: AiChatState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(aiChatState.messages.size) {
                when (val message = aiChatState.messages[it]) {
                    is ChatMessage.UserMessage -> Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MessageBubble(
                            message = message.text,
                            backgroundColor = Color.Green,
                        )
                    }

                    is ChatMessage.ModelMessage -> MessageBubble(
                        message = message.text,
                        backgroundColor = Color.Cyan,
                    )

                    is ChatMessage.StatusMessage -> Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MessageBubble(
                            message = message.text,
                            backgroundColor = Color.Yellow,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageBubble(
    message: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(Dimens.grid_1),
        modifier = modifier.padding(Dimens.grid_0_5)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(Dimens.grid_1)
        )
    }
}

