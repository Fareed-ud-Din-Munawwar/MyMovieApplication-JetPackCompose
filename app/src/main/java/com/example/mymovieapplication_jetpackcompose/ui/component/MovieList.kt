package com.example.mymovieapplication_jetpackcompose.ui.component

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieList(
    movieList: List<MovieEntity>,
    posterAddress: String,
    modifier: Modifier = Modifier,
    navigation: NavController,
    viewModel: MovieViewModel,
) {
    val scope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val showButton = remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn(state = state) {
                items(movieList) { item ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = 5.dp,
                        backgroundColor = MaterialTheme.colors.surface,
                        onClick = {
                            viewModel.setMovieDetail(item)
                            navigation.navigate("MovieDetailScreen")
                        }
                    ) {
                        ItemMovieList(item, posterAddress)
                    }
                }
            }

    AnimatedVisibility(
        visible = showButton.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        GoToTop {
            scope.launch { state.animateScrollToItem(0) } }
        }
    }
}




