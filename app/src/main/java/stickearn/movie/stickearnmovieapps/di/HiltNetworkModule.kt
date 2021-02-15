package stickearn.movie.stickearnmovieapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import stickearn.movie.stickearnmovieapps.network.client.StickEarnMovieClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltNetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = StickEarnMovieClient().getMovieDbServices()
}