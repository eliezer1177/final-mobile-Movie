package com.d121211065.tmdb.data.response

import com.d121211065.tmdb.data.models.Result
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class GetFilmResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<Result>?,
    @SerialName("total_pages")
    val total_pages: Int?,
    @SerialName("total_results")
    val total_results: Int?
)
