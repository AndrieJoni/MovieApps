package com.example.moviedetails.moviedetails.adapter

import androidx.paging.DataSource
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetMovieReviewsUseCase
import kotlinx.coroutines.CoroutineScope
import com.example.moviedetails.moviedetails.MovieReviewModel

class ReviewsMovieDataSourceFactory(
    private val id: String,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieReviewModel>() {

    private var reviewsMovieDataSource: ReviewsMovieDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieReviewModel> {

        reviewsMovieDataSource = ReviewsMovieDataSource(
            id,
            paginationStatus,
            getMovieReviewsUseCase,
            scope
        )

        return reviewsMovieDataSource as ReviewsMovieDataSource
    }
}
