package com.corbettcode.mymovies.navigation

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable

internal sealed class MovieSection(
    val icon: (@Composable () -> Unit) = {
        Icon(
            contentDescription = "movie",
            imageVector = Icons.Filled.Movie,
        )
    },
    val parameterName: String = "",
    val parameterPath: String = "",
    val route: String,
    val title: String = "",
) {
    data object MovieDetail : MovieSection(
        route = "movie_detail",
        parameterName = "id",
        parameterPath = "/{id}"
    )
}