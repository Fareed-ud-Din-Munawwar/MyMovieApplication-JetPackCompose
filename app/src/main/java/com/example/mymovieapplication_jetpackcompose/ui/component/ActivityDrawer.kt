package com.example.mymovieapplication_jetpackcompose.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieapplication_jetpackcompose.R

@Composable
fun ActivityDrawer(darkTheme: Boolean, onThemeChanged:(Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.enable_dark_theme),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Switch(checked = darkTheme, onCheckedChange = {
            onThemeChanged(!darkTheme)
        })
    }
}