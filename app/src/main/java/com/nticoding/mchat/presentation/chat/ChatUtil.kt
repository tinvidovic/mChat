package com.nticoding.mchat.presentation.chat

import com.nticoding.mchat.domain.model.Message
import com.nticoding.mchat.presentation.chat.Constants.HOURS_FOR_SECTION
import com.nticoding.mchat.presentation.chat.Constants.SECONDS_FOR_TAIL
import com.nticoding.mchat.presentation.components.ConversationSection
import com.nticoding.mchat.presentation.components.Message
import com.nticoding.mchat.util.TimeUnitsUtil

object ChatUtil {

    /**
     * Returns true if a [ConversationSection] should be shown before the current message
     * A section is shown if it is the first message in a conversation or if the previous message was
     * sent more than [HOURS_FOR_SECTION] hour ago
     */
    fun shouldShowSection(message: Message, previousMessage: Message?): Boolean {

        if (previousMessage == null)
            return true

        return (message.timestamp - previousMessage.timestamp) > TimeUnitsUtil.MILLIS_IN_HOUR * HOURS_FOR_SECTION
    }

    /**
     * Returns true if a [Message] should have a tail
     * A tail is shown if it is the last message in a conversation , if the next message was sent more
     * than [SECONDS_FOR_TAIL] seconds ago or if the next message was sent by another user
     */
    fun shouldShowTail(message: Message, nextMessage: Message?): Boolean {

        if (nextMessage == null)
            return true

        if (message.authorId != nextMessage.authorId)
            return true

        return (nextMessage.timestamp - message.timestamp) > TimeUnitsUtil.MILLIS_IN_SECOND * SECONDS_FOR_TAIL
    }
}