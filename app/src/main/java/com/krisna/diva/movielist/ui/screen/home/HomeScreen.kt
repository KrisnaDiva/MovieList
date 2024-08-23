package com.krisna.diva.movielist.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.krisna.diva.movielist.ui.components.layout.MovieSection
import com.krisna.diva.movielist.ui.components.layout.PopularMovieRow
import com.krisna.diva.movielist.ui.components.util.ResourceStateHandler
import com.krisna.diva.movielist.ui.components.layout.TopMovieColumn
import com.krisna.diva.movielist.ui.model.Movie

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Movie) -> Unit,
) {
    val popularMoviesState by viewModel.getPopularMovies().observeAsState()
    val topRatedMoviesState by viewModel.getTopRatedMovies().observeAsState()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovieSection(
            title = "Popular Movies",
            content = {
                ResourceStateHandler(
                    state = popularMoviesState,
                    successContent = { movies ->
                        PopularMovieRow(
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
                        TopMovieColumn(
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