package com.example.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Navigation {

    fun openMovieDetails(context: Context, bundle: Bundle) =
        internalIntent(context, "com.example.moviedetails")
            .putExtras(bundle)

    fun openFavoriteMovie(context: Context) =
        internalIntent(context, "com.example.moviedetails")

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)

    const val MOVIE_DATA = "MovieData"
}