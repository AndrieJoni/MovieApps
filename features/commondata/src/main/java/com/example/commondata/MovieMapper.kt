package com.example.commondata

import com.example.basedata.local.MovieEntity
import com.example.commondata.response.movie.MovieListResponse
import com.example.commondata.response.review.MoviesReviewResponse
import com.movie.domain.entity.Movie
import com.movie.domain.entity.MovieReview

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

fun MoviesReviewResponse.toReviewEntity(): List<MovieReview> {

    return listOfMoviesReviews.map {
        MovieReview(
            id = it.id,
            author = it.author,
            content = it.content,
            url = it.url
        )
    }
}

fun Movie.toMovieEntity(): MovieEntity {

    return MovieEntity(
        id = id,
        title = title,
        releaseDate = releaseDate,
        description = overview,
        imageUrl = posterPath
    )
}

fun MovieEntity.toMovie(): Movie {

    return Movie(
        id = id,
        title = title.toString(),
        releaseDate = releaseDate,
        overview = description.toString(),
        posterPath = imageUrl.toString()
    )
}

