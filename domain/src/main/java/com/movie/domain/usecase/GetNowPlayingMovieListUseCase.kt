package com.movie.domain.usecase

import com.movie.domain.UseCaseWithParam
import com.movie.domain.entity.Movie
import com.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseWithParam<String, List<Movie>> {

    override suspend fun invoke(parameter: String): List<Movie> {
        return movieRepository.getNowPlayingMovieList(page = parameter)
    }
}