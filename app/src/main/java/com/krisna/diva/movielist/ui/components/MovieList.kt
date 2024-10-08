package com.krisna.diva.movielist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krisna.diva.movielist.core.domain.model.Movie

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    navigateToDetail: (Movie) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                modifier = Modifier.clickable {
                    navigateToDetail(movie)
                }
            )
        }
    }
}

