package com.example.home

import com.example.commonui.MovieModel

fun interface OnMovieListener {
    fun onMovieClicked(movieData: MovieModel)
}