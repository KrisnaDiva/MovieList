package com.krisna.diva.movielist.ui.components.navigation

import android.net.Uri
import com.google.gson.Gson
import com.krisna.diva.movielist.ui.model.Movie

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/{movie}") {
        fun createRoute(movie: Movie): String {
            val movieJson = Uri.encode(Gson().toJson(movie))
            return "home/$movieJson"
        }
    }
}