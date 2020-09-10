package stickearn.movie.stickearnmovieapps.view.movieFavorite

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_favorite_movie.view.*
import stickearn.movie.stickearnmovieapps.BuildConfig
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.database.MovieEntity

class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(movieEntity: MovieEntity) {

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieEntity.imageUrl
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(itemView.ivFavoriteMovie)

        itemView.tvMovieTitle.text = movieEntity.title

        itemView.tvMovieReleaseDate.text = movieEntity.releaseDate

        itemView.tvMovieDescription.text = movieEntity.description
    }
}