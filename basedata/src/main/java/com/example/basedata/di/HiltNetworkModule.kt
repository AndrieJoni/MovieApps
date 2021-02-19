package com.example.basedata.di

import com.example.basedata.remote.Remote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltNetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Remote().getMovieDbServices()
}