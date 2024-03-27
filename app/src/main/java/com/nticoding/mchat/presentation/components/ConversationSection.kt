package com.nticoding.mchat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.nticoding.mchat.ui.theme.Citadel
import com.nticoding.mchat.ui.theme.MChatTheme
import com.nticoding.mchat.ui.theme.Quicksilver
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Composable representing the Conversation section divider
 * @param timestamp The time in millis since the epoch
 */
@Composable
fun ConversationSection(
    timestamp: Long,
    modifier: Modifier = Modifier,
    dayStyle: TextStyle = MaterialTheme.typography.labelMedium.copy(
        color = Citadel
    ),
    timeStyle: TextStyle = MaterialTheme.typography.labelMedium.copy(
        color = Quicksilver
    )
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = getDayFromTimestamp(timestamp),
            style = dayStyle,
        )

        Text(
            text = getTimeFromTimestamp(timestamp),
            style = timeStyle
        )
    }
}

/**
 * Returns full local day name from the UNIX timestamp
 * @param timestamp Unix timestamp in millis
 */
private fun getDayFromTimestamp(timestamp: Long): String {
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(timestamp)
}

/**
 * Returns HH:mm from the UNIX timestamp
 * @param timestamp Unix timestamp in millis
 */
private fun getTimeFromTimestamp(timestamp: Long): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(timestamp)
}

@Preview
@Composable
fun ConversationSectionPreview() {

    MChatTheme {
        ConversationSection(
            timestamp = System.currentTimeMillis(),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}