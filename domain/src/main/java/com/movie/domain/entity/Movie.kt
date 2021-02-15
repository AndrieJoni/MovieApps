package com.movie.domain.entity

data class Movie(

    var posterPath: String = "",

    var isAdult: Boolean = false,

    var overview: String = "",

    var releaseDate: String = "",

    var genreIds: ArrayList<Int> = arrayListOf(),

    var id: Int = 0,

    var originalTitle: String = "",

    var originalLanguage: String = "",

    var title: String = "",

    var backdropPath: String = "",

    var popularity: Double = 0.0,

    var voteCount: Long = 0L,

    var isVideo: Boolean = false,

    var voteAverage: Double = 0.0
)