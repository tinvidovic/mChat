package com.nticoding.mchat.presentation.chat

sealed class ChatEvent {

    data class OnInputChange(val input: String) : ChatEvent()

    object OnSendClick : ChatEvent()
}