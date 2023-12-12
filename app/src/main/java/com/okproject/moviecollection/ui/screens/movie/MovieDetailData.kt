package com.okproject.moviecollection.ui.screens.movie

data class MovieDetailData(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val tagline: String,
    val poster: String,
    val releaseYear: Int,
    val genres: String,
    val productionCompanies: String,
    val productionCountries: String,
    val overview: String
)
