package com.movie.domain.repository

import com.movie.domain.entity.Movie

interface MovieRepository {

    suspend fun getDetailsMovie(): Movie

    suspend fun getTopRatedMovieList(page : String): List<Movie>

    suspend fun getNowPlayingMovieList(page : String): List<Movie>

    suspend fun getPopularMovieList(page : String): List<Movie>

    suspend fun getReviewOfMovie()

    suspend fun getFavoriteMovieList()
}