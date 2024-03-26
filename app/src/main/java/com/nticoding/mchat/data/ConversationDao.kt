package com.nticoding.mchat.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nticoding.mchat.data.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Query(
        """
            SELECT * 
            FROM messages
            WHERE conversation_id = :conversationId
            ORDER BY timestamp ASC
        """
    )
    fun getMessagesForConversation(conversationId: Int): Flow<List<MessageEntity>>
}