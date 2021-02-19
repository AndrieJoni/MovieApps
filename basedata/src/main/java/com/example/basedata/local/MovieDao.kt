package com.example.basedata.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT id,title,release_date,overview,image FROM movie")
    fun getAllMovies(): DataSource.Factory<Int, FavoriteMovieEntity>

    @Query("SELECT * FROM movie WHERE id == :id")
    suspend fun findMovieById(id: Int): List<FavoriteMovieEntity>

    @Insert
    suspend fun insertMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}