package com.corbettcode.mymovies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.Navigator
import mymovies.composeapp.generated.resources.Res
import mymovies.composeapp.generated.resources.app_title
import mymovies.composeapp.generated.resources.artist_detail
import mymovies.composeapp.generated.resources.movie_detail
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
        MovieSection.MovieDetail.route -> stringResource(Res.string.movie_detail)
        MovieSection.ArtistDetail.route -> stringResource(Res.string.artist_detail)
        else -> {
            stringResource(Res.string.app_title)
        }
    }
}