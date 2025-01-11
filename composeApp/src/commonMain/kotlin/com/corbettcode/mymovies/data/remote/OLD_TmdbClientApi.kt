package pe.fernan.kmp.tmdb.data.remote

import com.corbettcode.mymovies.data.extension.toJson
import com.corbettcode.mymovies.data.model.ResponseList
import com.corbettcode.mymovies.data.model.ResponseMovieList
import com.corbettcode.mymovies.data.model.ResponseSearchMovie
import com.corbettcode.mymovies.data.model.ResponseSearchTvSeries
import com.corbettcode.mymovies.data.model.ResponseTVSeriesList
import com.corbettcode.mymovies.data.model.ResponseTrending
import com.corbettcode.mymovies.domain.model.ResultMovie
import com.corbettcode.mymovies.domain.model.ResultTvSeries
import com.corbettcode.mymovies.ui.common.Constant
import com.corbettcode.mymovies.ui.common.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object TmdbClientApi {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

    suspend fun getTrendingAll(timeWindows: String): ResponseTrending {
        val url = Constant.getTrendingAllUrl(timeWindows)
        return client.get(url).body()
    }

    val sortByOptions = listOf(
        "popularity.asc",
        "popularity.desc",
        "revenue.asc",
        "revenue.desc",
        "primary_release_date.asc",
        "primary_release_date.desc",
        "vote_average.asc",
        "vote_average.desc",
        "vote_count.asc",
        "vote_count.desc"
    )
    /*

    GET /3/discover/movie?include_adult=false&page=1&sort_by=popularity.asc
    GET /3/discover/tv?include_adult=true&page=1&sort_by=popularity.desc


     */


    suspend fun getDiscoverAll(mediaType: String): ResponseTrending {
        val url = Constant.getDiscoverAllUrl(mediaType)
        return client.get(url).body()
    }

    suspend fun getMovieList(movieListType: String): ResponseMovieList {
        val url = Constant.getMovieListUrl(movieListType)
        return client.get(url).body()
    }


    suspend fun getTVSeriesList(tvSeriesListType: String): ResponseTVSeriesList {
        val url = Constant.getTvSeriesUrl(tvSeriesListType)
        return client.get(url).body()
    }


    suspend fun getList(keyType: String, key: String): ResponseList {
        val url = Constant.getListUrl(keyType, key)
        return client.get(url).body()
    }
    suspend fun getMovie(tmdbId: Int): ResultMovie {
        val url = Constant.getTmdbMovieUrl(tmdbId)
        return client.get(url).body()
    }

    suspend fun getTvSeries(tmdbId: Int): ResultTvSeries {
        val url = Constant.getTmdbTvSeriesUrl(tmdbId)
        return client.get(url).body()
    }

    suspend fun getSearchTvSeriesList(query: String): ResponseSearchTvSeries? {
        val url = Constant.getTmdbTvSearchUrl(query)
        return client.get(url).body()
    }

    suspend fun getSearchMovieList(query: String): ResponseSearchMovie? {
        val url = Constant.getTmdbMovieSearchUrl(query)
        return client.get(url).body()
    }
}


suspend fun main() = coroutineScope {
    launch {
        //println(TmdbClientApi.getTrendingAll(TimeWindows.DAY).toJson())
        println(TmdbClientApi.getTvSeries(456).toJson())
    }
    println("Hello")
}




