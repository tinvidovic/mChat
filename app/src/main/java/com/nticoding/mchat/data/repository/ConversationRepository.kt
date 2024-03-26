package com.nticoding.mchat.data.repository

import com.nticoding.mchat.data.ConversationDao
import com.nticoding.mchat.data.entity.MessageEntity
import com.nticoding.mchat.domain.Message
import com.nticoding.mchat.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversationRepository(
    private val dao: ConversationDao,
) : ConversationRepository {
    override suspend fun insertMessage(message: Message, conversationId: Int) {
        val messageEntity = MessageEntity(
            authorId = message.authorId,
            content = message.content,
            timestamp = message.timestamp,
            conversationId = conversationId
        )

        dao.insertMessage(messageEntity)
    }

    override suspend fun getMessagesForConversation(conversationId: Int): Flow<List<Message>> {

        return dao.getMessagesForConversation(conversationId)
            .map { messageEntities ->
                messageEntities.map {
                    Message(
                        authorId = it.authorId,
                        content = it.content,
                        timestamp = it.timestamp
                    )
                }
            }
    }
}