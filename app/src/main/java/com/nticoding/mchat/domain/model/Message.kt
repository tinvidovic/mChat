package com.nticoding.mchat.domain.model

import java.io.Serializable

/**
 * Represents a chat message
 * @property authorId The UUID of the author of the message
 * @property content The string content of the message
 * @property timestamp Message sent timestamp in millis from epoch
 */
data class Message(
    val authorId: Int,
    val content: String,
    val timestamp: Long,
) : Serializable