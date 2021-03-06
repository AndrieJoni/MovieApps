package com.example.home.topRated

import androidx.paging.DataSource
import com.example.commonui.MovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetTopRatedMovieListUseCase
import kotlinx.coroutines.CoroutineScope

class TopRatedMoviesDataSourceFactory(
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieModel>() {

    var topRatedMoviesDataSource: TopRatedMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieModel> {

        topRatedMoviesDataSource = TopRatedMoviesDataSource(
            paginationStatus,
            getTopRatedMovieListUseCase,
            scope
        )

        return topRatedMoviesDataSource as TopRatedMoviesDataSource
    }
}
