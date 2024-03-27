package com.nticoding.mchat.presentation

import com.nticoding.mchat.domain.model.Message
import com.nticoding.mchat.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class ConversationRepositoryFake: ConversationRepository {

    private var messages = listOf(
        Message(authorId = 0, content = "Hi", timestamp = 1711526400000), // 27.3.2024 08:00:00 GMT
        Message(
            authorId = 0,
            content = "How are you?",
            timestamp = 1711526410000
        ), // 27.3.2024 08:00:10 GMT
        Message(
            authorId = 0,
            content = "Wyd?",
            timestamp = 1711526415000
        ), // 27.3.2024 08:00:15 GMT
        Message(authorId = 1, content = "Hey", timestamp = 1711526460000), // 27.3.2024 08:01:00 GMT
        Message(
            authorId = 1,
            content = "Not much",
            timestamp = 1711526481000
        ), // 27.3.2024 08:01:21 GMT
        Message(authorId = 1, content = "U?", timestamp = 1711526520000), // 27.3.2024 08:02:00 GMT
        Message(
            authorId = 0,
            content = "Same",
            timestamp = 1711530121000
        ), // 27.3.2024 09:02:01 GMT
        Message(
            authorId = 0,
            content = "Chilling, Killing",
            timestamp = 1711533721000
        ), // 27.3.2024 10:02:01 GMT
        Message(
            authorId = 1,
            content = "Cool",
            timestamp = 1711533739000
        )) // 27.3.2024 10:02:19 GMT
        set(value) {
            field = value
            messageFlow.value = value
        }

    private val messageFlow = MutableStateFlow(messages)
    override suspend fun insertMessage(message: Message, conversationId: Int) {

        val newMessageList = messages.toMutableList()

        var index = 0
        while (index < newMessageList.size && newMessageList[index].timestamp < message.timestamp) {
            index++
        }

        newMessageList.add(index, message)

        messages = newMessageList
    }

    override suspend fun getMessagesForConversation(conversationId: Int): Flow<List<Message>> = channelFlow {

        messageFlow.collectLatest {
            // Send in reverse order, because the layout is reversed
            send(it.reversed())
        }
    }
}