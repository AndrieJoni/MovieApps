package stickearn.movie.stickearnmovieapps

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import stickearn.movie.stickearnmovieapps.di.databaseModule
import stickearn.movie.stickearnmovieapps.di.movieModule
import stickearn.movie.stickearnmovieapps.di.networkModule

@HiltAndroidApp
class StickEarnMovieApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@StickEarnMovieApplication)

            ///CoreModule
            modules(networkModule, databaseModule)

            ///MovieModule
            modules(movieModule)
        }
    }
}