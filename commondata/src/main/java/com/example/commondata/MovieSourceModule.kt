package com.example.commondata

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit


@Module
@InstallIn(ActivityRetainedComponent::class)
class MovieSourceModule {

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieRemoteSource =
        retrofit.create(MovieRemoteSource::class.java)
}