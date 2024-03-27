package com.nticoding.mchat.domain.use_case

import com.nticoding.mchat.domain.model.Message
import com.nticoding.mchat.domain.repository.ConversationRepository

class InsertMessageUseCase(
    private val conversationRepository: ConversationRepository,
) {

    /**
     * Inserts a message to the corresponding conversation
     * @param conversationId The id of the conversation
     */
    suspend operator fun invoke(
        message: Message,
        conversationId: Int,
    ) {

        conversationRepository.insertMessage(
            message = message,
            conversationId = conversationId
        )
    }
}