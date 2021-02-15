package stickearn.movie.stickearnmovieapps.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import stickearn.movie.stickearnmovieapps.database.MovieDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltDatabaseModule {

    @Provides
    @Singleton
    fun providesDb(@ApplicationContext appContext: Context): MovieDatabase = Room.databaseBuilder(
        appContext,
        MovieDatabase::class.java,
        "MovieDatabase"
    ).build()
}