package com.movie.domain.usecase

import com.movie.domain.UseCaseWithParam
import com.movie.domain.entity.Movie
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMovieListFromLocalUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParam<Int, List<Movie>> {

    override suspend fun invoke(parameter: Int): List<Movie> {

        return if (parameter == 0) {
            movieRepository.getAllMovieFromLocal(15)
        } else {
            movieRepository.getAllMoviesFromLocalAfter(parameter, 15)
        }
    }
}