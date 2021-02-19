package com.example.commondata

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