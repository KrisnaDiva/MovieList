package com.krisna.diva.movielist.ui.navigation

import android.net.Uri
import com.google.gson.Gson
import com.krisna.diva.movielist.core.domain.model.Movie

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/{movie}") {
        fun createRoute(movie: Movie): String {
            val movieJson = Uri.encode(Gson().toJson(movie))
            return "home/$movieJson"
        }
    }
}