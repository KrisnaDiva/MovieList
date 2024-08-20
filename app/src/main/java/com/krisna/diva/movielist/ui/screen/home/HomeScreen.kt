package com.krisna.diva.movielist.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.ui.components.ErrorScreen
import com.krisna.diva.movielist.ui.components.LoadingScreen
import com.krisna.diva.movielist.ui.components.MovieItem
import com.krisna.diva.movielist.ui.components.PopularMovieItem
import com.krisna.diva.movielist.ui.model.Movie

@Preview(showBackground = true, device = Devices.PIXEL_6)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val popularMoviesState by viewModel.popularMovies.observeAsState()
    val topRatedMoviesState by viewModel.topRatedMovies.observeAsState()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MovieSection(
            title = "Popular Movies",
            content = {
                ResourceStateHandler(
                    state = popularMoviesState,
                    successContent = { movies -> PopularMovieRow(movies = movies) }
                )
            }
        )

        MovieSection(
            title = "Top Rated Movies",
            content = {
                ResourceStateHandler(
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
fun <T> ResourceStateHandler(
    state: Resource<T>?,
    loadingContent: @Composable () -> Unit = { LoadingScreen() },
    errorContent: @Composable (String) -> Unit = { ErrorScreen(errorMessage = it) },
    successContent: @Composable (T) -> Unit
) {
    when (state) {
        is Resource.Loading -> loadingContent()
        is Resource.Error -> state.message?.let { errorContent(it) }
        is Resource.Success -> state.data?.let { successContent(it) }
        else -> {}
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
    movies: List<Movie>?,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies ?: emptyList()) { movie ->
            PopularMovieItem(movie)
        }
    }
}

@Composable
fun TopMoviesColumn(
    movies: List<Movie>?,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(movies ?: emptyList()) { movie ->
            MovieItem(movie)
        }
    }
}

