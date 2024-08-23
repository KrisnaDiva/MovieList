package com.krisna.diva.movielist.ui.components.util

import androidx.compose.runtime.Composable
import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.ui.components.display.ErrorScreen
import com.krisna.diva.movielist.ui.components.display.LoadingScreen

@Composable
fun <T> ResourceStateHandler(
    state: Resource<T>?,
    loadingContent: @Composable () -> Unit = { LoadingScreen() },
    errorContent: @Composable (String) -> Unit = { ErrorScreen(errorMessage = it) },
    successContent: @Composable (T) -> Unit
) {
    when (state) {
        is Resource.Loading -> loadingContent()
        is Resource.Error -> state.message?.let { errorContent(it) }
        is Resource.Success -> state.data?.let { successContent(it) }
        else -> {}
    }
}