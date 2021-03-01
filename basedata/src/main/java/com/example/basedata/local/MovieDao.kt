package com.example.basedata.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY id ASC limit :requestedLoadSize")
    fun getAllMovies(requestedLoadSize: Int): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id > :key ORDER BY id ASC limit :requestedLoadSize")
    fun getAllMoviesAfter(key: Int, requestedLoadSize: Int): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id == :id")
    suspend fun findMovieById(id: Int): List<MovieEntity>

    @Insert
    suspend fun insertMovie(movieEntity: MovieEntity): Long

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}