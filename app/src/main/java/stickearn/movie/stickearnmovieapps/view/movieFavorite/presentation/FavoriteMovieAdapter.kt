package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.view.movieFavorite.data.FavoriteMovieEntity

class FavoriteMovieAdapter : PagedListAdapter<FavoriteMovieEntity, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_favorite_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        getItem(position)?.let { (holder as FavoriteMovieViewHolder).renderView(it) }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovieEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovieEntity,
                newItem: FavoriteMovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovieEntity,
                newItem: FavoriteMovieEntity
            ): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }
}