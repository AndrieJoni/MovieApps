package stickearn.movie.stickearnmovieapps.view.movieFavorite.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import stickearn.movie.stickearnmovieapps.repository.MovieRepository
import com.example.basedata.local.FavoriteMovieEntity

class FavoriteMovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun getFavoriteMoviesData(): LiveData<PagedList<com.example.basedata.local.FavoriteMovieEntity>> {
        return LivePagedListBuilder(
            movieRepository.getFavoriteMovies(),
            initializePageConfig()
        ).build()
    }
}