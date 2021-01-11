package stickearn.movie.stickearnmovieapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import stickearn.movie.stickearnmovieapps.database.MovieDatabase
import stickearn.movie.stickearnmovieapps.network.MovieDbService
import stickearn.movie.stickearnmovieapps.repository.MovieRepository

@Module
@InstallIn(ActivityComponent::class)
class FavoriteMovieModule {

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieDbService =
        retrofit.create(MovieDbService::class.java)

    @Provides
    fun providesFavoriteRepository(movieDbService: MovieDbService, movieDatabase: MovieDatabase) =
        MovieRepository(movieDbService, movieDatabase)
}