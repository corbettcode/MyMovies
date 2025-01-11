package com.corbettcode.mymovies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.corbettcode.mymovies.ui.theme.Purple500

@Composable
internal fun TopAppBarWithArrow(
    backEnabled: Boolean = false,
    title: String?,
    pressOnBack: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Purple500,
        elevation = 6.dp,
        modifier = Modifier.height(50.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            if (backEnabled == true) {
                Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = null,
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            pressOnBack()
                        }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text (
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                style = MaterialTheme.typography.h6,
                text = title ?: "",
            )
        }

    }
}