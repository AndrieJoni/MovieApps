package com.example.basedata.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "movie", indices = [Index(value = arrayOf("id"), unique = true)])
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "overview") val description: String?,
    @ColumnInfo(name = "image") val imageUrl: String?
)