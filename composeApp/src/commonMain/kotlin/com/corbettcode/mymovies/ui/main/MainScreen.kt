package com.corbettcode.mymovies.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.corbettcode.mymovies.ui.ApplicationViewModel
import kotlinx.coroutines.launch
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(viewModel: ApplicationViewModel) {
    PreComposeApp {
        val navigator = rememberNavigator()

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            rememberTopAppBarState()
        )

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = { Text("Top Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )


            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize().padding(it)
                ) {

                    TmdbNavigationDrawer(drawerState = drawerState, navigationClose = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }, onNavigationItemSelected = { route ->
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigator.navigate(Screen.Items.passObject(route))
                    }) {
                        // Content
                        NavHost(
                            navigator = navigator, initialRoute = Screen.Home.route
                        ) {

                            scene(route = Screen.Home.route) {
                                HomeScreen(viewModel, onClickSearch = { query ->
                                    navigator.navigate(Screen.Search.pass(query))

                                }, onItemClick = { result ->
                                    navigator.navigate(Screen.Details.passObject(result))

                                })
                            }
                            scene(route = Screen.Details.route) { backStackEntry ->
                                Napier.d { "ToScreen-Details route ${backStackEntry.route}" }
                                Napier.d { "ToScreen-Details pathMap ${backStackEntry.pathMap}" }
                                val result = Screen.Details.getObject(backStackEntry.pathMap)
                                var youtubeUrl by remember { mutableStateOf("") }
                                var openDialog by remember { mutableStateOf(false) }
                                DetailScreen(result, viewModel, object : DetailScreenClick {
                                    override fun onTrailerClick(url: String) {
                                        youtubeUrl = url
                                        openDialog = true
                                        // It is not necessary to navigate since it is a dialog
                                        //navigator.navigate(Screen.Youtube.passUrl(url))
                                    }
                                })
                                if (openDialog) {
                                    YoutubeDialogScreen(youtubeUrl, onClose = {
                                        openDialog = false
                                    })
                                }


                            }

                            /*
                            scene(route = Screen.Youtube.route) { backStackEntry ->
                                val youtubeUrl = Screen.Youtube.getUrl(backStackEntry.pathMap)
                                YoutubeDialogScreen(youtubeUrl)
                            }

                             */

                            scene(route = Screen.Items.route) { backStackEntry ->
                                val navigateRoute = Screen.Items.getObject(backStackEntry.pathMap)
                                ItemsScreen(navigateRoute, viewModel, onItemSelected = { result ->
                                    navigator.navigate(Screen.Details.passObject(result))

                                })
                            }

                            scene(route = Screen.Search.route) { backStackEntry ->
                                val query = Screen.Search.getQuery(backStackEntry.pathMap)
                                SearchScreen(query, viewModel, onItemSelected = { result ->
                                    navigator.navigate(Screen.Details.passObject(result))
                                })
                            }


                        }

                    }
                }


            },

        )
    }
}

