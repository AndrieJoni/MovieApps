package com.example.commonui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterImage: String?,
    val backdropImage: String?
) : Parcelable