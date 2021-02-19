package com.example.home.popular

import androidx.recyclerview.widget.RecyclerView
import com.example.commondata.BuildConfig
import com.example.home.MovieHomeModel
import com.example.home.R
import com.example.home.databinding.AdapterPopularMoviesBinding
import com.squareup.picasso.Picasso

class PopularMoviesViewHolder(val adapterPopularMoviesBinding: AdapterPopularMoviesBinding) :

    RecyclerView.ViewHolder(adapterPopularMoviesBinding.root) {

    fun renderView(movieData: MovieHomeModel) {

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieData.backdropImage
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(adapterPopularMoviesBinding.ivPopularMovies)
    }
}