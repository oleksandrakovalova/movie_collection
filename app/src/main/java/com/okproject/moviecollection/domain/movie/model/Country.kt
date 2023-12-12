package com.okproject.moviecollection.domain.movie.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("iso_3166_1")
    val iso: String,
    val name: String
)
