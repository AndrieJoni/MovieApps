package com.example.home

import com.movie.domain.entity.Movie

fun Movie.toMovieHomeModel(): MovieHomeModel {
    return MovieHomeModel(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterImage = posterPath,
        backdropImage = backdropPath
    )
}