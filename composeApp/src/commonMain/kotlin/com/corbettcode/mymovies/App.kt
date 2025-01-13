package com.corbettcode.mymovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.corbettcode.mymovies.di.ApplicationModule
import com.corbettcode.mymovies.navigation.MovieSection
import com.corbettcode.mymovies.navigation.currentRoute
import com.corbettcode.mymovies.navigation.isBottomBarVisible
import com.corbettcode.mymovies.navigation.navigationTitle
import com.corbettcode.mymovies.ui.ApplicationViewModel
import com.corbettcode.mymovies.ui.components.DestinationScaffold
import com.corbettcode.mymovies.ui.components.SearchBar
import com.corbettcode.mymovies.ui.components.TopAppBarWithArrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.BackHandler
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@OptIn(ExperimentalCoroutinesApi::class)
@Preview
internal fun App(
    applicationViewModel: ApplicationViewModel = ApplicationModule.applicationViewModel,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    PreComposeApp {
        var isAppBarVisible by remember { mutableStateOf(true) }
        val isLoading by applicationViewModel.isLoading.collectAsState()
        val navigator = rememberNavigator()
        val pagerState = rememberPagerState { 2 }

        BackHandler(isAppBarVisible.not()) {
            isAppBarVisible = true
        }
        val currentRoute = currentRoute(navigator)

        MaterialTheme {
            Column(
                modifier = Modifier.consumeWindowInsets(
                    if (isAppBarVisible == true) {
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    }
                    else {
                        WindowInsets( 0, 0, 0, 0)
                    }
                )
            ) {
                if (isAppBarVisible == true) {
                    TopAppBarWithArrow(
                        backEnabled = isBackButtonEnable(navigator),
                        title = navigationTitle(navigator),
                    ) {
                        navigator.popBackStack()
                    }
                }
                else {
                    SearchBar(
                        viewModel = applicationViewModel,
                        pagerState = pagerState,
                    ) {
                        isAppBarVisible = true
                    }
                }

                if (isBottomBarVisible(navigator)) {
                    KMPNavigationSuiteScaffold(
                        navigationSuiteItems = {
                            items.forEach { destination ->
                                item(
                                    selected = destination.route == currentRoute,
                                    onClick = {
                                        navigator.navigate(
                                            destination.route,
                                            NavOptions(launchSingleTop = true),
                                        )
                                    },
                                    icon = destination.navIcon,
                                    label = { Text(text = destination.title, fontSize = 12.sp) },
                                )
                            }
                        },
                        windowAdaptiveInfo = windowAdaptiveInfo,
                    ) {
                        DestinationScaffold(
                            navigator = navigator,
                            applicationViewModel = applicationViewModel,
                            isAppBarVisible = isAppBarVisible,
                            isLoading = isLoading,
                            pagerState = pagerState,
                            onAppBarVisibilityChange = { isAppBarVisible = it }
                        )
                    }

                }
                else {
                    DestinationScaffold(
                        navigator = navigator,
                        applicationViewModel = applicationViewModel,
                        isAppBarVisible = isAppBarVisible,
                        isLoading = isLoading,
                        pagerState = pagerState,
                        onAppBarVisibilityChange = { isAppBarVisible = it }
                    )
                }            }
        }


    }



//    MaterialTheme {
//        var showCountries by remember { mutableStateOf(false) }
//        var timeAtLocation by remember { mutableStateOf("No location selected") }
//
//        Column(modifier = Modifier.padding(20.dp)) {
//            Text(
//                text = timeAtLocation,
//                style = TextStyle(fontSize = 20.sp),
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//                    .align(Alignment.CenterHorizontally)
//            )
//            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
//                DropdownMenu(
//                    expanded = showCountries,
//                    onDismissRequest = { showCountries = false }
//                ) {
//                    defaultCountries.forEach { (name, zone) ->
//                        DropdownMenuItem(
//                            text = { Text(name) },
//                            onClick = {
//                                timeAtLocation = currentTimeAt(name, zone)
//                                showCountries = false
//                            }
//                        )
//                    }
//                }
//            }
//            Button(
//                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
//                onClick = { showCountries = !showCountries }) {
//                Text("Select location")
//            }
//        }
//    }
}

@Composable
fun isBackButtonEnable(navigator: Navigator): Boolean {
    return when (currentRoute(navigator)) {
        MovieSection.ArtistDetail.route,
        MovieSection.MovieDetail.route,
        MovieSection.TvSeriesDetail.route ->
        {
            true
        }
        else -> {
            false
        }
    }
}
