package com.nticoding.mchat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.ui.theme.MChatTheme

@Composable
fun UserInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    maxLines: Int = 6,
    buttonEnabled: Boolean = true,
) {

    Surface(
        shadowElevation = 8.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextEntryBox(
                value = value,
                onValueChange = onValueChange,
                textStyle = textStyle,
                maxLines = maxLines,
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp
                    )
                    .weight(1F)
                    .testTag("UserInputTextEntryBox")
            )

            SendButton(
                onClick = onSend,
                enabled = buttonEnabled,
                modifier = Modifier.padding(
                    horizontal = 8.dp
                )
            )
        }
    }

}

@Preview
@Composable
fun UserInputPreview() {

    var text by remember { mutableStateOf("") }

    MChatTheme {
        UserInput(
            value = text,
            onValueChange = { text = it },
            onSend = { text = "" },
            buttonEnabled = text.isNotEmpty()
        )
    }
}