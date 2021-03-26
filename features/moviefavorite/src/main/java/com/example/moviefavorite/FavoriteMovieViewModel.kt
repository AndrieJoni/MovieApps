package com.example.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.commonui.MovieModel
import com.movie.domain.usecase.GetFavoriteMovieListFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val getFavoriteMovieListFromLocalUseCase: GetFavoriteMovieListFromLocalUseCase
) : ViewModel() {

    var favoriteMovieDataSourceFactory: FavoriteMovieDataSourceFactory? = null

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun getFavoriteMoviesData(): LiveData<PagedList<MovieModel>> {

        favoriteMovieDataSourceFactory = FavoriteMovieDataSourceFactory(
            getFavoriteMovieListFromLocalUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            favoriteMovieDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }
}