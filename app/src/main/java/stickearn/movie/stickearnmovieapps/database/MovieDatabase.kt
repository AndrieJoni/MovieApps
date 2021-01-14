package stickearn.movie.stickearnmovieapps.database

import androidx.room.Database
import androidx.room.RoomDatabase
import stickearn.movie.stickearnmovieapps.view.movieFavorite.data.MovieDao
import stickearn.movie.stickearnmovieapps.view.movieFavorite.data.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}