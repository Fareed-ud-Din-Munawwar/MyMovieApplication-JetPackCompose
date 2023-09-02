package com.example.mymovieapplication_jetpackcompose.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object MainScreenDestination : NavigationDestination {

    override fun route(): String = BOOK_DETAILS_BOTTOM_NAV_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = emptyList()


    private const val MAIN_SCREEN_ROUTE = "main_screen"
    private const val BOOK_DETAILS_BOTTOM_NAV_ROUTE = "$MAIN_SCREEN_ROUTE"

}