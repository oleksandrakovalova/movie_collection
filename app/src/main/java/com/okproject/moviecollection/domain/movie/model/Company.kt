package com.okproject.moviecollection.domain.movie.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: Int,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)
