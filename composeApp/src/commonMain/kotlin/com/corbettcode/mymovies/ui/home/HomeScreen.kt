package com.corbettcode.mymovies.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.corbettcode.mymovies.ui.common.Constant
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    var search by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val state = rememberLazyListState()

        LazyColumn(
            state = state,
            //modifier = Modifier.fillMaxSize().padding(it)
        ) {
            item {

            }
        }
    }
}


@Composable
fun SectionInit(
    backgroundPath: String,
    search: String,
    onSearchValueChange: (String) -> Unit,
    onClickSearch: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {

        val backDrop = Constant.TMDB_IMAGE_BASE_PATH_CARD_HORIZONTAL_SUPER_LARGE + backgroundPath
        val painter = rememberImagePainter(backDrop)
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }

        val blueColor = Color(0xFF022541)
        val blueColor2 = Color(0xFF016B9A)


        val colorStops = arrayOf(
            0.15f to blueColor.copy(0.8f),
            0.75f to blueColor2.copy(0.7f),
            1f to blueColor2.copy(0.6f)
        )

        val gradient = Brush.horizontalGradient(colorStops = colorStops)

        val localCurrentSize = LocalCurrentSize()

        Box(
            modifier = Modifier.fillMaxWidth().height(localCurrentSize.height * 0.4f)
        ) {


            CoilImage(
                imageModel = { backDrop }, // loading a network image or local resource using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),

                modifier = Modifier.fillMaxSize().onGloballyPositioned {
                    sizeImage = it.size
                }
            )
//            Image(painter = painter,
//                contentDescription = "image",
//                contentScale = ContentScale.Crop,
//                colorFilter = ColorFilter.tint(
//                    blueColor2, blendMode = BlendMode.Color
//                ),
//                modifier = Modifier.fillMaxSize().onGloballyPositioned {
//                    sizeImage = it.size
//                })
            Box(modifier = Modifier.matchParentSize().background(gradient))
        }

        Column(modifier = Modifier.padding(paddingInternal)) {
            Text(
                text = "Welcome",
                style = titleTextStyle,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "Millions of movies, TV shows and people to discover. Explore now.",
                style = subTitleTextStyle,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )
            Spacer(Modifier.size(paddingInternal / 1.5f))

            val shape = RoundedCornerShape(25.dp)
            //val hintTextSearch = if(LocalWindowSizeClass.current)
            CustomTextField(modifier = Modifier.fillMaxWidth(),
                value = search,
                onValueChange = onSearchValueChange,
                shape = shape,
                label = {
                    Text(
                        "Search for a movie, tv show, person......", color = Color.LightGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                trailingIcon = {
                    val gradientColors = listOf(
                        MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.tertiary
                    )
                    Button(
                        modifier = Modifier.padding(0.dp),
                        onClick = {
                            onClickSearch()
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        //shape = RoundedCornerShape(cornerRadius)
                    ) {

                        Box(
                            modifier = Modifier.background(
                                brush = Brush.linearGradient(colors = gradientColors), shape = shape
                            ).padding(horizontal = 16.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Search", color = Color.White
                            )
                        }
                    }
                })

        }
    }
}

