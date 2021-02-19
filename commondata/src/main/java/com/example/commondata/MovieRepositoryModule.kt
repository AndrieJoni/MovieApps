package com.example.commondata

import com.movie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    abstract fun providesMovieRepository2(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}