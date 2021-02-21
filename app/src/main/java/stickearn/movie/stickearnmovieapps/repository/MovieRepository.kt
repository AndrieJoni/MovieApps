package stickearn.movie.stickearnmovieapps.repository

import com.example.basedata.local.MovieEntity
import com.example.basedata.local.MovieDatabase
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDatabase: MovieDatabase
) {

    fun getFavoriteMovies() = movieDatabase.movieDao().getAllMovies()

    suspend fun insertMovies(movieEntity: MovieEntity) =
        movieDatabase.movieDao().insertMovie(movieEntity)

    suspend fun deleteMovies(movieEntity: MovieEntity) =
        movieDatabase.movieDao().deleteMovie(movieEntity)

    suspend fun findMovieById(id: Int) = movieDatabase.movieDao().findMovieById(id)
}