package com.nticoding.mchat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A DB entity representing a conversation
 * @see README
 */
@Entity(
    tableName = "conversations",
)
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
)