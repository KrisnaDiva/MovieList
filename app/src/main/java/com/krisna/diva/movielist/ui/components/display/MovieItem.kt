package com.krisna.diva.movielist.ui.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.krisna.diva.movielist.ui.model.Movie
import com.krisna.diva.movielist.ui.theme.MovieListTheme

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Row {
            AsyncImage(
                model = movie.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
            ) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                MovieRating(
                    rating = movie.rating,
                    modifier = Modifier
                        .offset(x = (-3).dp)

                )

                LazyRow(
                    modifier = Modifier
                        .padding(top = 6.dp),
                ) {
                    items(movie.genres) { genre ->
                        MovieGenre(genre = genre)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieItemPreview() {
    MovieListTheme {
        Column {
//            MovieItem(
//                image = R.drawable.fastx,
//                title = "Fast X",
//                rating = 8.115,
//                genres = listOf("Action", "Adventure", "Sci-Fi", "Thriller", "Crime", "Drama")
//            )
        }
    }
}