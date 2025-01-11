package com.corbettcode.mymovies.ui.details.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.corbettcode.mymovies.utility.NetworkConstant
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import mymovies.composeapp.generated.resources.Res
import mymovies.composeapp.generated.resources.artist_detail
import mymovies.composeapp.generated.resources.biography
import mymovies.composeapp.generated.resources.birth_day
import mymovies.composeapp.generated.resources.place_of_birth
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArtistDetailView(
    personId: Int,
    viewModel: ArtistDetailViewModel = viewModel { ArtistDetailViewModel() }
) {
    val artistDetailData by viewModel.artistDetailResponse.collectAsState()
    val progressBar by viewModel.isLoading.collectAsState()

    LaunchedEffect(true) {
        viewModel.artistDetail(personId)
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(
            DefaultBackgroundColor
        ).padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        if (progressBar) {
            ProgressIndicator()
        }
        artistDetailData?.let {
            Row {
                CoilImage(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(250.dp)
                        .width(190.dp)
                        .cornerRadius(10),
                    imageModel = {
                        NetworkConstant.IMAGE_URL.plus(it.profilePath)
                    },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        contentDescription = "artist image",
                        colorFilter = null,
                    ),
                    component = rememberImageComponent {
                        +CircularRevealPlugin(
                            duration = 800
                        )
                    },
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = artistDetailData?.name ?: "",
                        color = FontColor,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Medium
                    )
                    PersonalInfo(
                        stringResource(Res.string.artist_detail),
                        it.knownForDepartment
                    )
                    PersonalInfo(
                        stringResource(Res.string.artist_detail),
                        it.gender.toString()
                    )
                    PersonalInfo(
                        stringResource(Res.string.birth_day), it.birthday ?: ""
                    )
                    PersonalInfo(
                        stringResource(Res.string.place_of_birth),
                        it.placeOfBirth ?: ""
                    )
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(Res.string.biography),
                color = SecondaryFontColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = it.biography
            )
        }
    }
}

@Composable
fun PersonalInfo(title: String, info: String) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)) {
        Text(
            text = title,
            color = SecondaryFontColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info, color = FontColor, fontSize = 16.sp
        )
    }
}