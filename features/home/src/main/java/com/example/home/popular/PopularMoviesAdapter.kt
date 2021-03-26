package com.example.home.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.commonui.MovieModel
import com.example.home.OnMovieListener
import com.example.home.databinding.AdapterPopularMoviesBinding

class PopularMoviesAdapter : PagedListAdapter<MovieModel, PopularMoviesViewHolder>(
    DIFF_CALLBACK
) {

    var onMovieListener: OnMovieListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            AdapterPopularMoviesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        getItem(position)?.let { holder.renderView(movieData = it) }

        holder.adapterPopularMoviesBinding.cvPopuplarMovies.setOnClickListener {
            getItem(position)?.let { it1 ->
                onMovieListener?.onMovieClicked(it1)
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
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
                return oldItem.title == newItem.title
            }
        }
    }
}