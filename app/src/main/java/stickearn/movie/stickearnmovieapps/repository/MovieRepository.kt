package stickearn.movie.stickearnmovieapps.repository

import com.example.basedata.local.FavoriteMovieEntity
import com.example.basedata.local.MovieDatabase
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDatabase: MovieDatabase
) {

    fun getFavoriteMovies() = movieDatabase.movieDao().getAllMovies()

    suspend fun insertMovies(favoriteMovieEntity: FavoriteMovieEntity) =
        movieDatabase.movieDao().insertMovie(favoriteMovieEntity)

    suspend fun deleteMovies(favoriteMovieEntity: FavoriteMovieEntity) =
        movieDatabase.movieDao().deleteMovie(favoriteMovieEntity)

    suspend fun findMovieById(id: Int) = movieDatabase.movieDao().findMovieById(id)
}