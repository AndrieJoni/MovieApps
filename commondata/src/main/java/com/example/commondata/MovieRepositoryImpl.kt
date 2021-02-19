package com.example.commondata

import com.movie.domain.entity.Movie
import com.movie.domain.entity.MovieReview
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteSource: MovieRemoteSource
) : MovieRepository {

    override suspend fun getTopRatedMovieList(page: String): List<Movie> {
        return movieRemoteSource.getTopRatedMovies(page = page).toMovieEntity()
    }

    override suspend fun getNowPlayingMovieList(page: String): List<Movie> {
        return movieRemoteSource.getNowPlayingMovies(page = page).toMovieEntity()
    }

    override suspend fun getPopularMovieList(page: String): List<Movie> {
        return movieRemoteSource.getPopularMovies(page = page).toMovieEntity()
    }

    override suspend fun getMovieReviews(movieId: String, page: String): List<MovieReview> {
        return movieRemoteSource.getMovieReviews(movieId, page).toReviewEntity()
    }

    override suspend fun getFavoriteMovieList() {
    }
}