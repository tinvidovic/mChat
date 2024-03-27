package com.nticoding.mchat.presentation.chat

import com.google.common.truth.Truth.assertThat
import com.nticoding.mchat.presentation.chat.ChatUtil.shouldShowSection
import com.nticoding.mchat.presentation.chat.ChatUtil.shouldShowTail
import org.junit.Test
import com.nticoding.mchat.presentation.generators.messages

class ChatUtilTest {

    @Test
    fun `shouldShowSection, first message, returns true`() {

        val firstMessageIndex = 0

        assertThat(
            shouldShowSection(
                messages[firstMessageIndex],
                messages.getOrNull(firstMessageIndex - 1)
            )
        ).isTrue()
    }

    @Test
    fun `shouldShowSection, previous message sent less than 1h ago, returns false`() {

        val messageIndices = listOf(1, 7)

        for (messageIndex in messageIndices) {
            assertThat(
                shouldShowSection(
                    messages[messageIndex],
                    messages.getOrNull(messageIndex - 1)
                )
            ).isFalse()
        }
    }

    @Test
    fun `shouldShowSection, previous message sent more than 1h ago, returns true`() {

        val messageIndex = 6

        assertThat(
            shouldShowSection(
                messages[messageIndex],
                messages.getOrNull(messageIndex - 1)
            )
        ).isTrue()
    }

    @Test
    fun `shouldShowTail, last message, returns true`() {

        val messageIndex = messages.size - 1

        assertThat(
            shouldShowTail(
                messages[messageIndex],
                messages.getOrNull(messageIndex + 1)
            )
        ).isTrue()
    }

    @Test
    fun `shouldShowTail, next message sent less than 20s ago, returns false`() {

        val messageIndex = 0

        assertThat(
            shouldShowTail(
                messages[messageIndex],
                messages.getOrNull(messageIndex + 1)
            )
        ).isFalse()
    }

    @Test
    fun `shouldShowTail, next message sent more than 20s ago, returns true`() {

        val messageIndices = listOf(2, 3, 4, 5, 6)

        for (messageIndex in messageIndices) {
            assertThat(
                shouldShowTail(
                    messages[messageIndex],
                    messages.getOrNull(messageIndex + 1)
                )
            ).isTrue()
        }
    }

    @Test
    fun `shouldShowTail, next message sent by different user, returns true`() {

        val messageIndex = 7

        assertThat(
            shouldShowTail(
                messages[messageIndex],
                messages.getOrNull(messageIndex + 1)
            )
        ).isTrue()
    }
}