package com.nticoding.mchat.presentation.components

import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nticoding.mchat.R
import com.nticoding.mchat.ui.theme.MChatTheme

/**
 * A composable representing a round clipped profile picture
 * @param pictureId The resource id of the image to present
 * @param size The size in [Dp] that the composable should take
 */
@Composable
fun ProfilePicture(
    @DrawableRes pictureId: Int,
    @Dimension size: Dp,
    modifier: Modifier = Modifier,
) {

    Image(
        painter = painterResource(id = pictureId),
        contentDescription = stringResource(id = R.string.profile_picture_cd),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(size))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(size)
            )
    )
}

@Preview
@Composable
fun ProfilePicturePreview() {

    MChatTheme {
        ProfilePicture(
            pictureId = R.drawable.stock_profile_picture,
            size = 128.dp
        )
    }
}