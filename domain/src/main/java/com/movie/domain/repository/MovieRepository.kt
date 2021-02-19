package com.movie.domain.repository

import com.movie.domain.entity.Movie
import com.movie.domain.entity.MovieReview

interface MovieRepository {

    suspend fun getTopRatedMovieList(page: String): List<Movie>

    suspend fun getNowPlayingMovieList(page: String): List<Movie>

    suspend fun getPopularMovieList(page: String): List<Movie>

    suspend fun getMovieReviews(movieId: String, page: String): List<MovieReview>

    suspend fun getFavoriteMovieList()
}