package com.nticoding.mchat.presentation.components

import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.R
import com.nticoding.mchat.ui.theme.DisabledAlpha
import com.nticoding.mchat.ui.theme.MChatTheme

/**
 * A composable representing a [Conversation] header
 * @param pictureId see [ProfilePicture]
 * @param pictureSize see [ProfilePicture]
 * @param name The name of the conversation
 * @param nameStyle The [TextStyle] for the name
 * @param onBackClick Callback invoked when back arrow icon is clicked
 * @param onBackClick Callback invoked when meatball menu icon is clicked
 */
@Composable
fun ConversationHeader(
    @DrawableRes pictureId: Int,
    @Dimension pictureSize: Dp,
    name: String,
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
    nameStyle: TextStyle = MaterialTheme.typography.titleSmall
) {

    Surface(
        shadowElevation = 8.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = stringResource(id = R.string.back_arrow_cd),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(pictureSize * 0.8F)
                        .clickable { onBackClick() }
                )

                ProfilePicture(
                    pictureId = pictureId,
                    size = pictureSize
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = name,
                    style = nameStyle
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_meatball_menu),
                contentDescription = stringResource(id = R.string.meatball_menu_cd),
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = DisabledAlpha),
                modifier = Modifier
                    .size(pictureSize)
                    .clickable { onMoreClick() }
            )

        }
    }
}

@Preview
@Composable
fun ConversationHeaderPreview() {

    MChatTheme {
        ConversationHeader(
            pictureId = (R.drawable.stock_profile_picture),
            pictureSize = 64.dp,
            name = "Sarah",
            onBackClick = {},
            onMoreClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}