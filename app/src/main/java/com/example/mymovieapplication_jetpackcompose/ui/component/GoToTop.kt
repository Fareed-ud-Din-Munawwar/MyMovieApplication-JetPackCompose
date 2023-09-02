package com.example.mymovieapplication_jetpackcompose.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoToTop(goToTop: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton( modifier = Modifier
            .padding(16.dp)
            .size(50.dp)
            .align(Alignment.BottomEnd),
            onClick = goToTop) {
            Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = null)
        }
    }
}