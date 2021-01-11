package stickearn.movie.stickearnmovieapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import stickearn.movie.stickearnmovieapps.network.client.StickEarnMovieClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltNetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = StickEarnMovieClient().getMovieDbServices()
}