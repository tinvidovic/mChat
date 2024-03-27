package com.nticoding.mchat.domain.mapper

import com.nticoding.mchat.data.entity.MessageEntity
import com.nticoding.mchat.domain.model.Message

fun MessageEntity.toMessage(): Message {

    return Message(
        authorId = authorId,
        content = content,
        timestamp = timestamp
    )
}