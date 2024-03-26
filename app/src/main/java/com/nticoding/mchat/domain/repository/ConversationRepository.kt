package com.nticoding.mchat.domain.repository

import com.nticoding.mchat.domain.Message
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    /**
     * Saves the passed in message
     */
    suspend fun insertMessage(
        message: Message,
        conversationId: Int
    )

    /**
     * Returns the messages in the corresponding conversation, sorted in ascending order by timestamp
     */
    suspend fun getMessagesForConversation(
        conversationId: Int
    ): Flow<List<Message>>
}