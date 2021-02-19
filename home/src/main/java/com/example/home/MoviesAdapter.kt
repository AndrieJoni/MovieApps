package com.example.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.home.databinding.AdapterMoviesBinding

class MoviesAdapter : PagedListAdapter<MovieHomeModel, MoviesViewHolder>(
    DIFF_CALLBACK
) {

    var onMovieListener: OnMovieListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            AdapterMoviesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        getItem(position)?.let { holder.renderView(movieData = it) }

        holder.adapterMoviesBinding.cvMovie.setOnClickListener {
            getItem(position)?.let { it1 -> onMovieListener?.onMovieClicked(it1) }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieHomeModel>() {
            override fun areItemsTheSame(
                oldItem: MovieHomeModel,
                newItem: MovieHomeModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieHomeModel,
                newItem: MovieHomeModel
            ): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}