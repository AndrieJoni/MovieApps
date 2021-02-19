package com.example.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieHomeModel(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterImage: String,
    val backdropImage: String
) : Parcelable

