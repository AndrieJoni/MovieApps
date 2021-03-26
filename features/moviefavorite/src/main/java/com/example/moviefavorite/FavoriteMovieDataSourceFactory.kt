package com.example.moviefavorite

import androidx.paging.DataSource
import com.example.commonui.MovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetFavoriteMovieListFromLocalUseCase
import kotlinx.coroutines.CoroutineScope

class FavoriteMovieDataSourceFactory(
    private val getFavoriteMovieListFromLocalUseCase: GetFavoriteMovieListFromLocalUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieModel>() {

    private var favoriteMovieDataSource: FavoriteMovieDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieModel> {

        favoriteMovieDataSource = FavoriteMovieDataSource(
            getFavoriteMovieListFromLocalUseCase,
            paginationStatus,
            scope
        )

        return favoriteMovieDataSource as FavoriteMovieDataSource
    }
}