package com.krisna.diva.movielist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.krisna.diva.movielist.R

@Composable
fun MovieRating(
    modifier: Modifier = Modifier,
    rating: Double,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall.copy(
        fontWeight = FontWeight.Light,
    )
) {
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = stringResource(id = R.string.star_icon_desc),
            tint = Color(0xFFFFD700),
        )
        Text(
            text = stringResource(id = R.string.rating_format, rating),
            style = textStyle
        )
    }
}