package com.corbettcode.mymovies.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
    class Dates(
        @SerialName("maximum")
        val maximum: String?,
        @SerialName("minimum")
        val minimum: String?
    )