package com.krisna.diva.movielist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.krisna.diva.movielist.ui.MovieApp
import com.krisna.diva.movielist.ui.theme.MovieListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListTheme {
                Surface {
                    MovieApp()
                }
            }
        }
    }
}