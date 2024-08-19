package com.krisna.diva.movielist.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krisna.diva.movielist.ViewModelFactory
import com.krisna.diva.movielist.di.Injection
import com.krisna.diva.movielist.model.Movie
import com.krisna.diva.movielist.ui.common.UiState
import com.krisna.diva.movielist.ui.components.ErrorScreen
import com.krisna.diva.movielist.ui.components.LoadingScreen
import com.krisna.diva.movielist.ui.components.MovieItem
import com.krisna.diva.movielist.ui.components.PopularMovieItem

@Preview(showBackground = true, device = Devices.PIXEL_6)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val popularMoviesState by viewModel.popularMoviesState.collectAsState()
    val topRatedMoviesState by viewModel.topRatedMoviesState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
    }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovieSection(
            title = "Popular Movies",
            content = {
                StateHandler(
                    state = popularMoviesState,
                    successContent = { movies ->
                        PopularMovieRow(movies = movies)
                    }
                )
            }
        )

        MovieSection(
            title = "Top Rated Movies",
            content = {
                StateHandler(
                    state = topRatedMoviesState,
                    successContent = { movies ->
                        TopMoviesColumn(
                            movies = movies,
                            modifier = Modifier.height(400.dp)
                        )
                    }
                )
            }
        )
    }
}

@Composable
fun <T> StateHandler(
    state: UiState<T>,
    loadingContent: @Composable () -> Unit = { LoadingScreen() },
    errorContent: @Composable (String) -> Unit = { ErrorScreen(errorMessage = it) },
    successContent: @Composable (T) -> Unit
) {
    when (state) {
        is UiState.Loading -> loadingContent()
        is UiState.Error -> errorContent(state.errorMessage)
        is UiState.Success -> successContent(state.data)
    }
}
@Composable
fun MovieSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        content()
    }
}

@Composable
fun PopularMovieRow(
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            PopularMovieItem(movie)
        }
    }
}

@Composable
fun TopMoviesColumn(
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}