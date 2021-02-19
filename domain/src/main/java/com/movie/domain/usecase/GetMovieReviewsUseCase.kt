package com.movie.domain.usecase

import com.movie.domain.UseCaseWithParam
import com.movie.domain.entity.MovieReview
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParam<Map<String, String>, List<MovieReview>> {

    override suspend fun invoke(parameter: Map<String, String>): List<MovieReview> {
        return movieRepository.getMovieReviews(
            movieId = parameter["movieId"].toString(),
            page = parameter["page"].toString()
        )
    }
}