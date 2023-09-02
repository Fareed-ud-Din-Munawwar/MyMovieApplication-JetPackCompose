package com.example.mymovieapplication_jetpackcompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.viewmodel.MovieViewModel

@Composable
fun MainScreen(movieList: List<MovieEntity>, viewModel: MovieViewModel, navigation: NavController) {
    Column(Modifier.padding(12.dp)) {
        MovieList(
            movieList = movieList,
            viewModel.getPosterAddress(),
            navigation = navigation,
            viewModel = viewModel,
        )
    }
}
