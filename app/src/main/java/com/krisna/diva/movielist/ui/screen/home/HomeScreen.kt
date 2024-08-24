package com.krisna.diva.movielist.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krisna.diva.movielist.core.domain.model.Movie
import org.koin.androidx.compose.koinViewModel
import com.krisna.diva.movielist.ui.components.MovieSection
import com.krisna.diva.movielist.ui.components.PopularMovieList
import com.krisna.diva.movielist.ui.components.ResourceStateHandler
import com.krisna.diva.movielist.ui.components.TopMovieList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Movie) -> Unit,
) {
    val popularMoviesState by viewModel.popularMoviesState.collectAsState()
    val topRatedMoviesState by viewModel.topRatedMoviesState.collectAsState()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovieSection(
            title = "Popular Movies",
            content = {
                ResourceStateHandler(
                    state = popularMoviesState,
                    successContent = { movies ->
                        PopularMovieList(
                            movies = movies,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                )
            }
        )

        MovieSection(
            title = "Top Rated Movies",
            content = {
                ResourceStateHandler(
                    state = topRatedMoviesState,
                    successContent = { movies ->
                        TopMovieList(
                            movies = movies,
                            modifier = Modifier.height(400.dp),
                            navigateToDetail = navigateToDetail,
                        )
                    }
                )
            }
        )
    }
}