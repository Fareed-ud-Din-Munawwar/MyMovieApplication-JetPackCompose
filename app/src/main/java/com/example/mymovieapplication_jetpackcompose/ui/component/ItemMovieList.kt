package com.example.mymovieapplication_jetpackcompose.ui.component

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovieapplication_jetpackcompose.R
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity

@Composable
fun ItemMovieList(movieEntity: MovieEntity, posterAddress: String, expanded: Boolean = false) {
    movieEntity.movie.let { movie ->
        Column {
            Spacer(modifier = Modifier.padding(4.dp))
            Row(modifier = Modifier.padding(0.dp, 4.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            Uri.parse(posterAddress).buildUpon()
                                .appendEncodedPath(movie.poster_path).build().toString()
                        )
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.icons8_no_image_100),
                    contentDescription = stringResource(R.string.description),
                    modifier = Modifier.size(175.dp)
                )
                Column(modifier = Modifier.padding(2.dp, 4.dp)) {
                    Text(text = movie.title, fontWeight = FontWeight.Bold)
                    Text(text = stringResource(id = R.string.release_date).plus(movie.release_date))
                    Text(text = stringResource(id = R.string.original_language).plus(movie.original_language))
                    Text(text = stringResource(id = R.string.rating).plus(movie.vote_average.toString()))

                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}
