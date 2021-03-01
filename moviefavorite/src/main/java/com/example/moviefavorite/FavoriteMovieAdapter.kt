package com.example.moviefavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.commonui.MovieModel
import com.example.moviefavorite.databinding.AdapterFavoriteMovieBinding

class FavoriteMovieAdapter : PagedListAdapter<MovieModel, FavoriteMovieViewHolder>(
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

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MovieModel>() {
                override fun areItemsTheSame(
                    oldItem: MovieModel,
                    newItem: MovieModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieModel,
                    newItem: MovieModel
                ): Boolean {
                    return oldItem.overview == newItem.overview
                }
            }
    }
}