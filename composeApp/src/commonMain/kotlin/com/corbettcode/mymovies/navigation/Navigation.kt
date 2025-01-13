package com.corbettcode.mymovies.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.corbettcode.mymovies.ui.details.artist.ArtistDetailView
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import mymovies.composeapp.generated.resources.Res
import mymovies.composeapp.generated.resources.app_title
import mymovies.composeapp.generated.resources.artist_detail
import mymovies.composeapp.generated.resources.movie_detail
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation(navigator: Navigator, page: Int) {
    NavHost(
        navigator = navigator,
        initialRoute = initialScreen(page),
    ) {
        scene(route = MovieSection.ArtistDetail.route.plus(MovieSection.ArtistDetail.parameterPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(MovieSection.ArtistDetail.parameterName)
            id?.let {
                ArtistDetailView(it)
            }
        }
//        scene(route = MovieSection.NowPlayingMovie.route) {
//            NowPlayingScreen(navigator)
//        }
//        scene(route = MovieSection.PopularMovie.route) {
//            PopularMovie(navigator)
//        }
//        scene(route = MovieSection.TopRatedMovie.route) {
//            TopRatedMovie(navigator)
//        }
//        scene(route = MovieSection.UpcomingMovie.route) {
//            UpcomingMovie(navigator)
//        }
        scene(route = MovieSection.MovieDetail.route.plus(MovieSection.MovieDetail.parameterPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(MovieSection.MovieDetail.parameterName)
            id?.let {
                MovieDetailView(it)
            }
        }
//        scene(route = MovieSection.AiringTodayTvSeries.route) {
//            AiringTodayTvSeries(navigator)
//        }
//        scene(route = MovieSection.OnTheAirTvSeries.route) {
//            OnTheAirTvSeries(navigator)
//        }
//        scene(route = MovieSection.PopularTvSeries.route) {
//            PopularTvSeries(navigator)
//        }
//        scene(route = MovieSection.TopRatedTvSeries.route) {
//            TopRatedTvSeries(navigator)
//        }
        scene(route = MovieSection.TvSeriesDetail.route.plus(MovieSection.TvSeriesDetail.parameterPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(MovieSection.TvSeriesDetail.parameterName)
            id?.let {
                TvSeriesDetailView(it)
            }
        }
    }
}

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

@OptIn(ExperimentalFoundationApi::class)
fun initialScreen(page: Int): String {
    return if (page == 0) {
        MovieSection.NowPlayingMovie.route
    } else {
        MovieSection.AiringTodayTvSeries.route
    }
}

@Composable
fun isBottomBarVisible(navigator: Navigator): Boolean {
    val visibleRoutes = setOf(
        MovieSection.NowPlayingMovie.route,
        MovieSection.PopularMovie.route,
        MovieSection.TopRatedMovie.route,
        MovieSection.UpcomingMovie.route,
        MovieSection.AiringTodayTvSeries.route,
        MovieSection.OnTheAirTvSeries.route,
        MovieSection.PopularTvSeries.route,
        MovieSection.TopRatedTvSeries.route
    )

    return currentRoute(navigator) in visibleRoutes
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