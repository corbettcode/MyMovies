package com.corbettcode.mymovies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.corbettcode.mymovies.navigation.MovieSection
import com.corbettcode.mymovies.navigation.currentRoute
import com.corbettcode.mymovies.ui.ApplicationViewModel
import com.corbettcode.mymovies.ui.theme.FloatingActionBackground
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.navigation.Navigator


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun DestinationScaffold(
    navigator: Navigator,
    applicationViewModel: ApplicationViewModel,
    isAppBarVisible: Boolean,
    isLoading: Boolean,
    pagerState: PagerState,
    onAppBarVisibilityChange: (Boolean) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        floatingActionButton = {
            val route = currentRoute(navigator)
            when {
                route != MovieSection.MovieDetail.route
                && route != MovieSection.ArtistDetail.route
                && route != MovieSection.TvSeriesDetail.route -> {
                    FloatingActionButton(
                        onClick = { onAppBarVisibilityChange(false) },
                        backgroundColor = FloatingActionBackground
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) { padding ->
        TabScreen(navigator, pagerState, padding)
        if (currentRoute(navigator) !== MovieSection.MovieDetail.route) {
            Column {
                if (isAppBarVisible.not()) {
                    if (pagerState.currentPage == 0) {
                        SearchForMovie(
                            navigator,
                            applicationViewModel.movieSearchData.value
                        ) {
                            onAppBarVisibilityChange(true)
                        }
                    } else {
                        SearchForTVSeries(
                            navigator,
                            applicationViewModel.tvSeriesSearchData.value
                        ) {
                            onAppBarVisibilityChange(true)
                        }
                    }
                    ProgressIndicator(isLoading)
                }
            }
        }
    }
}
