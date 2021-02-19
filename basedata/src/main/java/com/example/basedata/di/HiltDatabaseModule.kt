package com.example.basedata.di

import android.content.Context
import androidx.room.Room
import com.example.basedata.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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