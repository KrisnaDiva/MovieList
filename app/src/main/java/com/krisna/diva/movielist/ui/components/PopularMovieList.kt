package com.krisna.diva.movielist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krisna.diva.movielist.core.domain.model.Movie

@Composable
fun PopularMovieList(
    movies: List<Movie>?,
    modifier: Modifier = Modifier,
    navigateToDetail: (Movie) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies ?: emptyList()) { movie ->
            PopularMovieItem(
                movie = movie,
                modifier = Modifier.clickable {
                    navigateToDetail(movie)
                }
            )
        }
    }
}
