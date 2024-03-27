package com.nticoding.mchat.presentation.chat

import com.nticoding.mchat.domain.model.Message
import java.io.Serializable

data class ChatState(
    val messages: List<Message> = emptyList(),
    val input: String = ""
): Serializable
