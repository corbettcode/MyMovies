package com.corbettcode.mymovies.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
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
    data object ArtistDetail : MovieSection(
        route = "artist_detail",
        parameterName = "id",
        parameterPath = "/{id}"
    )
    data object MovieDetail : MovieSection(
        route = "movie_detail",
        parameterName = "id",
        parameterPath = "/{id}"
    )
    data object TvSeriesDetail : MovieSection(
        route = "tv_series_detail",
        parameterName = "id",
        parameterPath = "/{id}"
    )
    data object NowPlayingMovie : MovieSection(route = "now_playing_movie")
    data object PopularMovie : MovieSection(route = "popular_movie")
    data object TopRatedMovie : MovieSection(route = "top_rated_movie")
    data object UpcomingMovie : MovieSection(route = "upcoming_movie")
    data object AiringTodayTvSeries : MovieSection(route = "airing_today_tv_series")
    data object OnTheAirTvSeries : MovieSection(route = "on_the_air_tv_series")
    data object PopularTvSeries : MovieSection(route = "popular_tv_series")
    data object TopRatedTvSeries : MovieSection(route = "top_rated_tv_series")
}