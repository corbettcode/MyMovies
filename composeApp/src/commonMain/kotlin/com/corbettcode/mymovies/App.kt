package com.corbettcode.mymovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import com.corbettcode.mymovies.di.ApplicationModule
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@ExperimentalFoundationApi
@Preview
fun App() {
    val viewModel by lazy {
        ApplicationModule.applicationViewModel
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

data class Country(val name: String, val zone: TimeZone)
fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

val defaultCountries = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo")),
    Country("France", TimeZone.of("Europe/Paris")),
    Country("Mexico", TimeZone.of("America/Mexico_City")),
    Country("Indonesia", TimeZone.of("Asia/Jakarta")),
    Country("Egypt", TimeZone.of("Africa/Cairo")),
)

fun todaysDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')

    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(zone).format()
}
