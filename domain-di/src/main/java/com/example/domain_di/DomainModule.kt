package com.example.domain_di

import com.movie.domain.repository.MovieRepository
import com.movie.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetFavoriteMovieListUseCase(movieRepository: MovieRepository) =
        GetFavoriteMovieListFromLocalUseCase(movieRepository)

    @Provides
    fun provideGetMovieReviewUseCase(movieRepository: MovieRepository) =
        GetMovieReviewsUseCase(movieRepository)

    @Provides
    fun provideGetNowPlayingMovieListUseCase(movieRepository: MovieRepository) =
        GetNowPlayingMovieListUseCase(movieRepository)

    @Provides
    fun provideGetPopularMovieListUseCase(movieRepository: MovieRepository) =
        GetPopularMovieListUseCase(movieRepository)

    @Provides
    fun provideGetTopRatedMovieListUseCase(movieRepository: MovieRepository) =
        GetTopRatedMovieListUseCase(movieRepository)

    @Provides
    fun provideIsMovieMarkedAsFavoriteUseCase(movieRepository: MovieRepository) =
        IsMovieMarkedAsFavoriteUseCase(movieRepository)

    @Provides
    fun provideMarkedMovieUseCase(
        movieRepository: MovieRepository,
        isMovieMarkedAsFavoriteUseCase: IsMovieMarkedAsFavoriteUseCase
    ) = MarkedMovieUseCase(movieRepository, isMovieMarkedAsFavoriteUseCase)
}