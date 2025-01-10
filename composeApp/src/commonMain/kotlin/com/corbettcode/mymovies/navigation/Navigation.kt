package com.corbettcode.mymovies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun currentRoute(navigator: Navigator): String {
    val routeName = navigator.currentEntry.collectAsState(null).value?.route?.route
    routeName?.let {
        return it.substringBefore("/")
    }
    ?: run {
        return ""
    }
}

@Composable
internal fun navigationTitle(navigator: Navigator): String {
    return when (currentRoute(navigator)) {
        MovieSection.MovieDetail.route -> stringResource(MR.string.movie_detail)
    }
}