package com.corbettcode.mymovies.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackz.kmmovies.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mymovies.composeapp.generated.resources.Res
import mymovies.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.Resource

@Composable
@ExperimentalFoundationApi
@Preview
fun App() {
    MaterialTheme {
        LaunchedEffect(true) {
            MovieRepository().getMovies {
                movieList = it.results
            }

        }
        Column {
            TopAppBar(
                title = {
                    Text(text = Greeting().greeting())
                },
                elevation = 2.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
                items(movieList) { movies ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        SampleImage(
                            movie = movies,
                            modifier = Modifier.fillMaxSize(),
                        )
                        Text(text = movies.title, maxLines = 1, fontSize = 17.sp)
                    }
                }
            }
        }
    }
}


@Composable
public fun SampleImage(movie :Movie, modifier: Modifier = Modifier) {

    val imageUrl: String = remember { ("https://image.tmdb.org/t/p/w185"+movie.posterPath) }

    when (val imageResource: Resource<Painter> = lazyPainterResource(imageUrl)) {
        is Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            KamelImage(resource = imageResource, null,contentScale = ContentScale.Crop, modifier = Modifier.width(180.dp).height(280.dp).clip(
                RoundedCornerShape(10.dp)
            ), crossfade = true)
        }
        is Resource.Failure -> {
            Text(text = imageResource.exception.message ?: "")
        }
    }
}

