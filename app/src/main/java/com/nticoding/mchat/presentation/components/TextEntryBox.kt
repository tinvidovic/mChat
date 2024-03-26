package com.nticoding.mchat.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.ui.theme.MChatTheme


/**
 * A composable representing a user input entry box
 * @param textStyle The [TextStyle] of the input text
 * @param maxLines Number of lines to expand before becoming scrollable
 */
@Composable
fun TextEntryBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    maxLines: Int = 6
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(48.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        textStyle = textStyle,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Preview
@Composable
fun EmptyTextEntryBoxPreview() {

    var text by remember { mutableStateOf("") }

    MChatTheme {
        TextEntryBox(
            text,
            {text = it}
        )
    }
}

@Preview
@Composable
fun TextEntryBoxPreview() {

    var text by remember { mutableStateOf("This is a preview!\nPreview me!") }

    MChatTheme {
        TextEntryBox(
            text,
            {text = it}
        )
    }
}