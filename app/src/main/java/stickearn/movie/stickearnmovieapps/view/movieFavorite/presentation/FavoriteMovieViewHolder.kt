package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.databinding.AdapterFavoriteMovieBinding

class FavoriteMovieViewHolder(
    val adapterFavoriteMovieBinding: AdapterFavoriteMovieBinding
) : RecyclerView.ViewHolder(
    adapterFavoriteMovieBinding.root
) {

    fun renderView(favoriteMovieEntity: com.example.basedata.local.FavoriteMovieEntity) {

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    com.example.commondata.BuildConfig.BASE_TMDB_IMAGE_URL,
                    favoriteMovieEntity.imageUrl
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(adapterFavoriteMovieBinding.ivFavoriteMovie)

        adapterFavoriteMovieBinding.tvMovieTitle.text = favoriteMovieEntity.title

        adapterFavoriteMovieBinding.tvMovieReleaseDate.text = LocalDate.parse(
            favoriteMovieEntity.releaseDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ).format(DateTimeFormatter.ofPattern("LLL dd,yyyy"))

        adapterFavoriteMovieBinding.tvMovieDescription.text = favoriteMovieEntity.description
    }
}