package stickearn.movie.stickearnmovieapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit
import stickearn.movie.stickearnmovieapps.network.MovieDbService

@Module
@InstallIn(ActivityRetainedComponent::class)
object MovieModule {

    @Provides
    fun providesMovieServiceConventional(retrofit: Retrofit): MovieDbService =
        retrofit.create(MovieDbService::class.java)
}