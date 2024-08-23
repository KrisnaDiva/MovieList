package com.krisna.diva.movielist.ui.components.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.krisna.diva.movielist.ui.components.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)