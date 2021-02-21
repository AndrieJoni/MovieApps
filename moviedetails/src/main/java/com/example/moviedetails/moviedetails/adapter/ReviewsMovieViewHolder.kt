package com.example.moviedetails.moviedetails.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.databinding.AdapterReviewMovieBinding
import com.example.moviedetails.moviedetails.MovieReviewModel

class ReviewsMovieViewHolder(
    private val adapterReviewMovieBinding: AdapterReviewMovieBinding
) : RecyclerView.ViewHolder(adapterReviewMovieBinding.root) {

    fun renderView(movieReviewData: MovieReviewModel) {

        adapterReviewMovieBinding.tvReviewAuthor.text = movieReviewData.author

        adapterReviewMovieBinding.tvReviewContent.text = movieReviewData.content
    }
}