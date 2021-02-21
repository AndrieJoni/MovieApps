package com.movie.domain.usecase

import com.movie.domain.UseCaseWithParam
import com.movie.domain.entity.Movie
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MarkedMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val isMovieMarkedAsFavoriteUseCase: IsMovieMarkedAsFavoriteUseCase
) : UseCaseWithParam<Movie, Boolean> {

    override suspend fun invoke(parameter: Movie): Boolean {

        return if (isMovieMarkedAsFavoriteUseCase.invoke(parameter)) {
            movieRepository.insertMovieToLocal(parameter)
            true
        } else {
            movieRepository.deleteMovieFromLocal(parameter)
            false
        }
    }
}