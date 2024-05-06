package com.br.kmpdemo.compose.ui.aichat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiChatScreen(
    aiChatState: AiChatState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1.0f)) {
            items(aiChatState.messages.value.size) {
                when (val message = aiChatState.messages.value[it]) {
                    is ChatMessage.UserMessage -> Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MessageBubble(message.text, Color.Green)
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

        // Handle user input -
        var userInput: String by remember { mutableStateOf("") }
        OutlinedTextField(
            value = userInput,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Yellow,
                focusedTextColor = Color.Black
            ),
            onValueChange = { userInput = it },
            keyboardActions = KeyboardActions {
                aiChatState.userInput(userInput)
                userInput = ""
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            singleLine = true,
        )
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
            modifier = Modifier.padding(Dimens.grid_1),
            color = Color.Black
        )
    }
}

