package com.krisna.diva.movielist.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.ui.components.MovieSection
import com.krisna.diva.movielist.ui.components.MovieList
import com.krisna.diva.movielist.ui.components.ResourceStateHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    navigateToDetail: (Movie) -> Unit,
) {
    val favoriteMovies by viewModel.favoriteMoviesState.collectAsState()

    MovieSection(
        modifier = modifier,
        title = "Favorite Movies",
        content = {
            ResourceStateHandler(
                state = favoriteMovies,
                successContent = { movies ->
                    MovieList(
                        movies = movies,
                        navigateToDetail = navigateToDetail,
                    )
                }
            )
        }
    )
}