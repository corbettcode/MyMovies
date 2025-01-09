package com.corbettcode.mymovies.data.model

import com.corbettcode.mymovies.domain.model.TmdbResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseTVSeriesList(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<TmdbResult?>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)