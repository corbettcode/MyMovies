package com.corbettcode.mymovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalFoundationApi::class)
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyMovies",
    ) {
        App()
    }
}