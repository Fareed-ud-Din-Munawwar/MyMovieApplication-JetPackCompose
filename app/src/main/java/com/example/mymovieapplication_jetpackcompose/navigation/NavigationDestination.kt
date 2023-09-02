package com.example.mymovieapplication_jetpackcompose.navigation

import androidx.navigation.NamedNavArgument

fun interface NavigationDestination {

    fun route(): String
    val arguments: List<NamedNavArgument>
        get() = emptyList()
}