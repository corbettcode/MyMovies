package com.corbettcode.mymovies.domain.repository

import com.corbettcode.mymovies.domain.model.ResultMovie
import com.corbettcode.mymovies.domain.model.ResultTvSeries
import com.corbettcode.mymovies.domain.model.TmdbResult

interface TmdbRepository {
    suspend fun getTrendingList(timeWindows: String): List<TmdbResult>?
    suspend fun getDiscoverAll(mediaType: String): List<TmdbResult>?
    suspend fun getMovieList(movieListType: String): List<TmdbResult>?
    suspend fun getTVSeriesList(tvSeriesListType: String): List<TmdbResult>?
    suspend fun getList(keyType: String, key: String): List<TmdbResult>?
    suspend fun getTvSeries(tmdbId: Int): ResultTvSeries
    suspend fun getMovie(tmdbId: Int): ResultMovie
    suspend fun getSearchList(query: String, mediaType: String): List<TmdbResult>?
}
