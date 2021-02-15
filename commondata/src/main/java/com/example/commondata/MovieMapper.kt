package com.example.commondata

import com.movie.domain.entity.Movie

fun MovieListResponse.toMovieEntity(): List<Movie> {

    return listOfMovies.map {
        Movie(
            posterPath = it.posterPath,
            isAdult = it.isAdult,
            overview = it.overview,
            releaseDate = it.releaseDate,
            genreIds = it.genreIds,
            id = it.id,
            originalTitle = it.originalTitle,
            originalLanguage = it.originalLanguage,
            title = it.title,
            backdropPath = it.backdropPath,
            popularity = it.popularity,
            voteCount = it.voteCount,
            isVideo = it.isVideo,
            voteAverage = it.voteAverage
        )
    }
}