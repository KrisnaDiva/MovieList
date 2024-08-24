package com.krisna.diva.movielist.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun MovieGenreList(
    modifier: Modifier = Modifier,
    genres: List<String>,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    LazyRow(modifier = modifier) {
        items(genres) { genre ->
            MovieGenre(
                genre = genre,
                textStyle = textStyle,
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        }
    }
}