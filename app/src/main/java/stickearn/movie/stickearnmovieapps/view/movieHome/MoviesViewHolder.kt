package stickearn.movie.stickearnmovieapps.view.movieHome

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movies.view.*
import kotlinx.android.synthetic.main.adapter_popular_movies.view.ivPopularMovies
import stickearn.movie.stickearnmovieapps.BuildConfig
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.data.MovieData

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(movieData: MovieData) {

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieData.posterPath
                )
            )
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(itemView.ivMovies)

        itemView.tvFilmName.text = movieData.title

        itemView.tvFilmReleaseDate.text = movieData.releaseDate
    }
}