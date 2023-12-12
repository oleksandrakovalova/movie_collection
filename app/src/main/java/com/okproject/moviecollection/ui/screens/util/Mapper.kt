package com.okproject.moviecollection.ui.screens.util

import com.okproject.moviecollection.data.networking.IMAGE_BASE_URL
import com.okproject.moviecollection.domain.movie.model.MovieDetailed
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import com.okproject.moviecollection.ui.screens.movie.MovieDetailData
import com.okproject.moviecollection.ui.screens.popular.MovieData

fun MovieSimplified.toListItemPresentation(): MovieData =
    MovieData(
        id = this.id,
        title = this.title,
        releaseYear = this.releaseDate.getYear(),
        poster = IMAGE_BASE_URL + this.posterPath
    )

fun MovieDetailed.toDetailsPresentation(): MovieDetailData =
    MovieDetailData(
        id = this.id,
        title = this.title,
        originalTitle = this.originalTitle,
        tagline = this.tagline,
        poster = IMAGE_BASE_URL + this.posterPath,
        releaseYear = this.releaseDate.getYear(),
        genres = this.genres
            .map { it.name }
            .joinToString { genre -> genre },
        productionCompanies = this.productionCompanies
            .map { it.name }
            .joinToString { company -> company },
        productionCountries = this.productionCountries
            .map { it.name }
            .joinToString { county -> county },
        overview = this.overview
    )