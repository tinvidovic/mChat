package com.nticoding.mchat.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * A DB entity representing a message in a conversation
 * @see README
 */
@Entity(
    tableName = "messages",
    // See db diagram in README
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = ["id"],
            childColumns = ["conversation_id"]
        )
    ]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "author_id")
    val authorId: Int,
    val content: String,
    val timestamp: Long,
    @ColumnInfo(name = "conversation_id")
    val conversationId: Int
)