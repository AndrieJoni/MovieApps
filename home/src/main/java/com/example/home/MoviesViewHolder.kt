package com.example.home

import androidx.recyclerview.widget.RecyclerView
import com.example.commondata.BuildConfig
import com.example.commonui.MovieModel
import com.example.home.databinding.AdapterMoviesBinding
import com.squareup.picasso.Picasso
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MoviesViewHolder(
    val adapterMoviesBinding: AdapterMoviesBinding
) : RecyclerView.ViewHolder(adapterMoviesBinding.root) {

    fun renderView(movieData: MovieModel) {

        Picasso.Builder(itemView.context).build()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieData.posterImage
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(adapterMoviesBinding.ivMovies)

        adapterMoviesBinding.tvFilmName.text = movieData.title

        adapterMoviesBinding.tvFilmReleaseDate.text = LocalDate.parse(
            movieData.releaseDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ).format(DateTimeFormatter.ofPattern("LLL dd,yyyy"))
    }
}