package com.example.commondata

import com.example.basedata.local.MovieDao
import com.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
object MovieSourceModule {

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieRemoteSource =
        retrofit.create(MovieRemoteSource::class.java)
}