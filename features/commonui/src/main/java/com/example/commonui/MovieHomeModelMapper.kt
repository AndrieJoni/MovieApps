package com.example.commonui

import com.movie.domain.entity.Movie

fun Movie.toMovieModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterImage = posterPath,
        backdropImage = backdropPath
    )
}

fun MovieModel.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterImage,
        backdropPath = backdropImage
    )
}

