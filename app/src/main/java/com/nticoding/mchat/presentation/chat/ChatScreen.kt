package com.nticoding.mchat.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nticoding.mchat.R
import com.nticoding.mchat.domain.model.Conversation
import com.nticoding.mchat.presentation.chat.ChatUtil.shouldShowSection
import com.nticoding.mchat.presentation.chat.ChatUtil.shouldShowTail
import com.nticoding.mchat.presentation.components.ConversationHeader
import com.nticoding.mchat.presentation.components.ConversationSection
import com.nticoding.mchat.presentation.components.Message
import com.nticoding.mchat.presentation.components.UserInput

@Composable
fun ChatScreen(
    conversation: Conversation,
    viewModel: ChatViewModel = hiltViewModel<ChatViewModel, ChatViewModel.ChatViewModelFactory> { factory ->
        factory.create(conversation.id)
    }
) {

    val state = viewModel.state

    val listState = rememberLazyListState()

    Column {

        ConversationHeader(
            pictureId = R.drawable.stock_profile_picture,
            pictureSize = 48.dp,
            name = "Sarah",
            onBackClick = { },
            onMoreClick = { viewModel.getRandomResponse() },
            modifier = Modifier
                .fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true,
            state = listState
        ) {
            state.messages.forEachIndexed { index, message ->

                val currentUserMessage = message.authorId == viewModel.myId

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (currentUserMessage) Arrangement.End else Arrangement.Start
                    ) {

                        val messageModifier = if (currentUserMessage) {
                            Modifier
                                .padding(
                                    start = 64.dp,
                                    end = 16.dp,
                                    top = 8.dp
                                )
                        } else {
                            Modifier
                                .padding(
                                    start = 16.dp,
                                    end = 64.dp,
                                    top = 8.dp
                                )
                        }

                        Message(
                            messageContent = message.content,
                            currentUserMessage = currentUserMessage,
                            hasTail = shouldShowTail(message, state.messages.getOrNull(index - 1)),
                            seen = currentUserMessage,
                            modifier = messageModifier
                        )
                    }
                }

                // Add section for first item (reverse layout, so added after message item)
                if (index == state.messages.size - 1 || shouldShowSection(
                        message,
                        state.messages[index + 1]
                    )
                ) {
                    item {
                        ConversationSection(
                            timestamp = message.timestamp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        UserInput(
            value = state.input,
            onValueChange = { viewModel.onEvent(ChatEvent.OnInputChange(it)) },
            onSend = { viewModel.onEvent(ChatEvent.OnSendClick) },
            buttonEnabled = state.input.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

object Constants {

    const val SECONDS_FOR_TAIL = 20

    const val HOURS_FOR_SECTION = 1
}