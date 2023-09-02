package com.example.mymovieapplication_jetpackcompose.ui.component

import android.net.Uri
import android.text.Layout.Alignment
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovieapplication_jetpackcompose.R
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(movieDetails: MovieEntity?, navigation: NavController, viewModel: MovieViewModel) {
    BackHandler {
        navigation.popBackStack()
    }
    Column(Modifier.padding(12.dp)) {
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            movieDetails?.let {
                MovieDetails(it, viewModel)
            }
        }
    }
}

@Composable
fun MovieDetails(movie: MovieEntity, viewModel: MovieViewModel) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(8.dp))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally,) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(
                    Uri.parse(viewModel.getPosterAddress()).buildUpon()
                        .appendEncodedPath(movie.movie.poster_path).build().toString()
                ).crossfade(true).build(),
                placeholder = painterResource(R.drawable.icons8_no_image_100),
                contentDescription = stringResource(R.string.description),
                modifier = Modifier.size(350.dp).fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = movie.movie.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.release_date).plus(movie.movie.release_date))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.original_language).plus(movie.movie.original_language))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.rating).plus(movie.movie.vote_average.toString()))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.overview).plus(movie.movie.overview.toString()))
            Spacer(modifier = Modifier.padding(8.dp))

        }

    }
}
