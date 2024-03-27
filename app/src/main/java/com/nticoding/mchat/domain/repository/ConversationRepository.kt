package com.nticoding.mchat.domain.repository

import com.nticoding.mchat.domain.model.Message
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
     * Returns the messages in the corresponding conversation, sorted in descending order by timestamp
     */
    suspend fun getMessagesForConversation(
        conversationId: Int
    ): Flow<List<Message>>
}