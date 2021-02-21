package com.example.commondata

import com.example.basedata.local.MovieDao
import com.movie.domain.entity.Movie
import com.movie.domain.entity.MovieReview
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteSource: MovieRemoteSource,
    private val movieDao: MovieDao,
) : MovieRepository {

    override suspend fun getMovieByIdFromLocal(movieId: Int): List<Movie> {
        return movieDao.findMovieById(movieId).map {
            it.toMovie()
        }
    }

    override suspend fun insertMovieToLocal(movie: Movie): Long {
        return movieDao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun deleteMovieFromLocal(movie: Movie): Boolean {
        movieDao.deleteMovie(movie.toMovieEntity())
        return true
    }

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