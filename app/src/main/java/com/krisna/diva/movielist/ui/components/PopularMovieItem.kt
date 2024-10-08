package com.krisna.diva.movielist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.krisna.diva.movielist.R
import com.krisna.diva.movielist.core.domain.model.Movie

@Composable
fun PopularMovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Card(
        modifier = modifier.width(140.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column {
            AsyncImage(
                model = movie.image,
                contentDescription = stringResource(id = R.string.movie_image_desc),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )

            MovieRating(
                rating = movie.rating,
                modifier = Modifier.offset(x = (-4).dp)
            )
        }
    }
}