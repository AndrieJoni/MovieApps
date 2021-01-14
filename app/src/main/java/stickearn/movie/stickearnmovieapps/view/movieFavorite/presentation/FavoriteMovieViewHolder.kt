package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_favorite_movie.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import stickearn.movie.stickearnmovieapps.BuildConfig
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.view.movieFavorite.data.FavoriteMovieEntity

class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(favoriteMovieEntity: FavoriteMovieEntity) {

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    BuildConfig.BASE_TMDB_IMAGE_URL,
                    favoriteMovieEntity.imageUrl
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(itemView.ivFavoriteMovie)

        itemView.tvMovieTitle.text = favoriteMovieEntity.title

        itemView.tvMovieReleaseDate.text = LocalDate.parse(
            favoriteMovieEntity.releaseDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ).format(DateTimeFormatter.ofPattern("LLL dd,yyyy"))

        itemView.tvMovieDescription.text = favoriteMovieEntity.description
    }
}