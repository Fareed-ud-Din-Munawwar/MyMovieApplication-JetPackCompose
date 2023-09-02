package com.example.mymovieapplication_jetpackcompose.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface Navigator {

    fun navigateUp(): Boolean
    fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    val destinations: Flow<NavigatorEvent>
}