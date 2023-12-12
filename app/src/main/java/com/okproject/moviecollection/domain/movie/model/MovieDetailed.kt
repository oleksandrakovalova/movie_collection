package com.okproject.moviecollection.domain.movie.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailed(
    val id: Long,
    val title: String,
    @SerialName("original_title")
    val originalTitle: String,
    val tagline: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val genres: List<Genre>,
    @SerialName("production_companies")
    val productionCompanies: List<Company>,
    @SerialName("production_countries")
    val productionCountries: List<Country>,
    val overview: String
)
