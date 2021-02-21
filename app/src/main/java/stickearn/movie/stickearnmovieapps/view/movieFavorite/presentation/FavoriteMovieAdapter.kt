package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import stickearn.movie.stickearnmovieapps.databinding.AdapterFavoriteMovieBinding

class FavoriteMovieAdapter : PagedListAdapter<com.example.basedata.local.MovieEntity, FavoriteMovieViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(
            AdapterFavoriteMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {

        getItem(position)?.let { holder.renderView(it) }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.example.basedata.local.MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: com.example.basedata.local.MovieEntity,
                newItem: com.example.basedata.local.MovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: com.example.basedata.local.MovieEntity,
                newItem: com.example.basedata.local.MovieEntity
            ): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }
}