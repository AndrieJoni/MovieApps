package com.movie.domain.usecase

import com.movie.domain.UseCaseWithParam
import com.movie.domain.entity.Movie
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class IsMovieMarkedAsFavoriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParam<Movie, Boolean> {

    override suspend fun invoke(parameter: Movie): Boolean {
        return movieRepository.getMovieByIdFromLocal(parameter.id).size == 1
    }
}