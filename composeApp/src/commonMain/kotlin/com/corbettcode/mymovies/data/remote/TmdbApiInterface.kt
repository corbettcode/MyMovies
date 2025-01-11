package data.remote

import com.corbettcode.mymovies.data.model.ResponseList
import com.corbettcode.mymovies.data.model.ResponseMovieList
import com.corbettcode.mymovies.data.model.ResponseTVSeriesList
import com.corbettcode.mymovies.data.model.artist.Artist
import com.corbettcode.mymovies.data.model.artist.ArtistDetail
import com.corbettcode.mymovies.data.model.movie.MovieDetail
import com.corbettcode.mymovies.data.model.tv.TvSeriesDetail
import com.corbettcode.mymovies.data.model.tv.credit.Credit


interface TmdbApiInterface {
    suspend fun nowPlayingMovieList(
        page: Int
    ): ResponseMovieList

    suspend fun popularMovieList(
        page: Int
    ): ResponseList

    suspend fun topRatedMovieList(
        page: Int
    ): ResponseList

    suspend fun upcomingMovieList(
        page: Int
    ): ResponseMovieList

    suspend fun movieDetail(
        movieId: Int
    ): MovieDetail

    suspend fun movieSearch(
        searchKey: String
    ): ResponseList

    suspend fun recommendedMovie(
        movieId: Int
    ): ResponseList
    suspend fun movieCredit(
        movieId: Int
    ): Artist
    suspend fun artistDetail(
        personId: Int
    ): ArtistDetail

    suspend fun airingTodayTvSeries(
        page: Int
    ): ResponseTVSeriesList

    suspend fun onTheAirTvSeries(
        page: Int
    ): ResponseTVSeriesList

    suspend fun popularTvSeries(
        page: Int
    ): ResponseTVSeriesList

    suspend fun topRatedTvSeries(
        page: Int
    ): ResponseTVSeriesList

    suspend fun tvSeriesDetail(
        seriesId: Int
    ): TvSeriesDetail

    suspend fun recommendedTvSeries(
        seriesId: Int
    ): ResponseTVSeriesList

    suspend fun creditTvSeries(
        seriesId: Int
    ): Credit

    suspend fun tvSeriesSearch(
        searchKey: String
    ): ResponseTVSeriesList

}