package com.nticoding.mchat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.R
import com.nticoding.mchat.domain.Message
import com.nticoding.mchat.ui.theme.MChatTheme
import java.util.UUID

/**
 * A Composable of a single Message
 * @param messageContent The string content of the message
 * @param currentUserMessage true if the message was sent by the user, false otherwise
 * @param hasTail true if the message should have a tail
 * @param seen true if the message should be marked as seen
 */
@Composable
fun Message(
    messageContent: String,
    currentUserMessage: Boolean,
    hasTail: Boolean,
    seen: Boolean,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 48f,
                    topEnd = 48f,
                    bottomStart = if (!currentUserMessage && hasTail) 0f else 48f,
                    bottomEnd = if (currentUserMessage && hasTail) 0f else 48f
                )
            )
            .background(if (currentUserMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
            )
    ) {

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = messageContent,
                style = MaterialTheme.typography.bodyMedium,
                color = if (currentUserMessage) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            )

            if (seen) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_seen),
                    contentDescription = stringResource(id = R.string.message_seen_cd),
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(6.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}

/* PREVIEWS */
private val previewMessage = Message(
    authorId = 0,
    content = "This is a preview message.\nPreview me!",
    timestamp = System.currentTimeMillis()
)

@Preview
@Composable
fun CurrentUserMessagePreview() {

    MChatTheme {
        Message(
            messageContent = previewMessage.content,
            currentUserMessage = true,
            hasTail = false,
            seen = false
        )
    }
}
@Preview
@Composable
fun CurrentUserTailedMessagePreview() {

    MChatTheme {
        Message(
            messageContent = previewMessage.content,
            currentUserMessage = true,
            hasTail = true,
            seen = true
        )
    }
}

@Preview
@Composable
fun OtherUserTailedMessagePreview() {

    MChatTheme {
        Message(
            messageContent = previewMessage.content,
            currentUserMessage = false,
            hasTail = true,
            seen = false
        )
    }
}

