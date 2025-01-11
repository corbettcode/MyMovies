package com.corbettcode.mymovies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.corbettcode.mymovies.navigation.MovieSection
import com.corbettcode.mymovies.navigation.Navigation
import com.corbettcode.mymovies.navigation.currentRoute
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import mymovies.composeapp.generated.resources.Res
import mymovies.composeapp.generated.resources.movies
import mymovies.composeapp.generated.resources.tv_series
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabScreen(navigator: Navigator, pagerState: PagerState, padding: PaddingValues) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(stringResource(Res.string.movies), stringResource(Res.string.tv_series))

    Column(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        if (currentRoute(navigator) !in listOf(
                MovieSection.MovieDetail.route,
                MovieSection.TvSeriesDetail.route,
                MovieSection.ArtistDetail.route
            )
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.PrimaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MaterialTheme.colors.primary,
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                title,
                                color = if (pagerState.currentPage == index) MaterialTheme.colors.primary else Color.Gray
                            )
                        })
                }
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            Navigation(navigator, page)
        }
    }
}
