package stickearn.movie.stickearnmovieapps.view.moviedetails.adapter

import androidx.recyclerview.widget.RecyclerView
import stickearn.movie.stickearnmovieapps.databinding.AdapterReviewMovieBinding
import stickearn.movie.stickearnmovieapps.view.moviedetails.MovieReviewModel

class ReviewsMovieViewHolder(
    val adapterReviewMovieBinding: AdapterReviewMovieBinding
) : RecyclerView.ViewHolder(adapterReviewMovieBinding.root) {

    fun renderView(movieReviewData: MovieReviewModel) {

        adapterReviewMovieBinding.tvReviewAuthor.text = movieReviewData.author

        adapterReviewMovieBinding.tvReviewContent.text = movieReviewData.content
    }
}