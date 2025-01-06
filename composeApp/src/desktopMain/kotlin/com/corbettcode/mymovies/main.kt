package com.corbettcode.mymovies

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.corbettcode.mymovies.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyMovies",
    ) {
        App()
    }
}