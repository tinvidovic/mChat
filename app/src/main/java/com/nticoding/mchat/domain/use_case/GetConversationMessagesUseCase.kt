package com.nticoding.mchat.domain.use_case

import com.nticoding.mchat.domain.model.Message
import com.nticoding.mchat.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GetConversationMessagesUseCase(
    private val conversationRepository: ConversationRepository,
) {

    /**
     * Returns a list of [Message] for the corresponding conversation, sorted in ASC timestamp order
     * @param conversationId The id of the conversation
     */
    suspend operator fun invoke(
        conversationId: Int,
    ): Flow<List<Message>> = channelFlow {

        conversationRepository.getMessagesForConversation(conversationId)
            .collectLatest { messages ->
                send(messages)
            }
    }
}