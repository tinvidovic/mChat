package com.nticoding.mchat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.R
import com.nticoding.mchat.ui.theme.DisabledAlpha
import com.nticoding.mchat.ui.theme.FuchsiaFelicity
import com.nticoding.mchat.ui.theme.LoveFumes
import com.nticoding.mchat.ui.theme.MChatTheme

@Composable
fun SendButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val topGradientColor =
        if (enabled) FuchsiaFelicity else FuchsiaFelicity.copy(alpha = DisabledAlpha)
    val bottomGradientColor = if (enabled) LoveFumes else LoveFumes.copy(alpha = DisabledAlpha)

    IconButton(
        onClick = onClick, modifier = modifier
            .clip(RoundedCornerShape(48.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        topGradientColor,
                        bottomGradientColor,
                    )
                )
            ),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Transparent
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = stringResource(id = R.string.send_cd),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun EnabledSendButtonPreview() {

    MChatTheme {
        SendButton(onClick = {})
    }
}

@Preview
@Composable
fun DisabledSendButtonPreview() {

    MChatTheme {
        SendButton(
            onClick = {}, enabled = false
        )
    }
}