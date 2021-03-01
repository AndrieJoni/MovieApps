package com.example.moviefavorite

import androidx.recyclerview.widget.RecyclerView
import com.example.commonui.MovieModel
import com.example.moviefavorite.databinding.AdapterFavoriteMovieBinding
import com.squareup.picasso.Picasso
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FavoriteMovieViewHolder(
    private val adapterFavoriteMovieBinding: AdapterFavoriteMovieBinding
) : RecyclerView.ViewHolder(
    adapterFavoriteMovieBinding.root
) {

    fun renderView(movieModel: MovieModel) {

        Picasso
            .Builder(itemView.context).build()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    com.example.commondata.BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieModel.posterImage
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(adapterFavoriteMovieBinding.ivFavoriteMovie)

        adapterFavoriteMovieBinding.tvMovieTitle.text = movieModel.title

        adapterFavoriteMovieBinding.tvMovieReleaseDate.text = LocalDate.parse(
            movieModel.releaseDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ).format(DateTimeFormatter.ofPattern("LLL dd,yyyy"))

        adapterFavoriteMovieBinding.tvMovieDescription.text = movieModel.overview
    }
}