package stickearn.movie.stickearnmovieapps.view.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import stickearn.movie.stickearnmovieapps.databinding.AdapterReviewMovieBinding
import stickearn.movie.stickearnmovieapps.view.moviedetails.MovieReviewModel

class ReviewsMovieAdapter : PagedListAdapter<MovieReviewModel, ReviewsMovieViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsMovieViewHolder {
        return ReviewsMovieViewHolder(
            AdapterReviewMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewsMovieViewHolder, position: Int) {

        getItem(position)?.let { holder.renderView(it) }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieReviewModel>() {
            override fun areItemsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem.content == newItem.content
            }
        }
    }
}