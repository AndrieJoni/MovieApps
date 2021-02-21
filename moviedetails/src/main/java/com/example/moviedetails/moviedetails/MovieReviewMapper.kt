package com.example.moviedetails.moviedetails

import com.movie.domain.entity.MovieReview

fun MovieReview.toMovieReviewModel(): MovieReviewModel {
    return MovieReviewModel(
        id = id,
        author = author,
        content = content
    )
}