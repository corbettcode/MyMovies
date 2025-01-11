package com.corbettcode.mymovies.data.remote

import com.corbettcode.mymovies.domain.model.MediaType
import com.corbettcode.mymovies.domain.model.ResultMovie
import com.corbettcode.mymovies.domain.model.ResultTvSeries
import com.corbettcode.mymovies.domain.model.TmdbResult
import com.corbettcode.mymovies.domain.repository.TmdbRepository
import pe.fernan.kmp.tmdb.data.remote.TmdbClientApi


class OLD_TmdbRepositoryActual(private val api: TmdbClientApi) : TmdbRepository {

    private fun fixMediaTypeIfNotFound(mediaType: String, list: List<TmdbResult?>?): List<TmdbResult>? {
        return list?.filterNotNull()?.map {
            if (it.mediaType == null) {
                return@map it.copy(mediaType = mediaType)
            }
            it
        }
    }

    override suspend fun getTrendingList(timeWindows: String) =
        api.getTrendingAll(timeWindows).results?.filterNotNull()

    override suspend fun getDiscoverAll(mediaType: String): List<TmdbResult>? =
        fixMediaTypeIfNotFound(mediaType, api.getDiscoverAll(mediaType).results)

    override suspend fun getMovieList(movieListType: String): List<TmdbResult>? =
        fixMediaTypeIfNotFound(MediaType.MOVIE.value, api.getMovieList(movieListType).results)

    override suspend fun getTVSeriesList(tvSeriesListType: String): List<TmdbResult>? =
        fixMediaTypeIfNotFound(MediaType.TV.value, api.getTVSeriesList(tvSeriesListType).results)

    override suspend fun getList(keyType: String, key: String): List<TmdbResult>? =
        api.getList(keyType, key).results?.filterNotNull()

    override suspend fun getTvSeries(tmdbId: Int): ResultTvSeries = api.getTvSeries(tmdbId)
    override suspend fun getMovie(tmdbId: Int): ResultMovie = api.getMovie(tmdbId)
    override suspend fun getSearchList(query: String, mediaType: String): List<TmdbResult>? {
        return fixMediaTypeIfNotFound(
            mediaType, when (mediaType) {
                MediaType.MOVIE.value -> {
                    api.getSearchMovieList(query)?.results?.filterNotNull()
                }

                MediaType.TV.value -> {
                    api.getSearchTvSeriesList(query)?.results?.filterNotNull()
                }

                else -> {
                    null
                }
            }
        )
    }

}

