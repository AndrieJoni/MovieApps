package com.example.domain_di

import com.example.basedata.local.MovieDao
import com.example.commondata.MovieRemoteSource
import com.example.commondata.MovieRepositoryImpl
import com.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesMovieRepository(
        movieRemoteSource: MovieRemoteSource,
        movieDao: MovieDao,
    ): MovieRepository = MovieRepositoryImpl(movieRemoteSource, movieDao)
}