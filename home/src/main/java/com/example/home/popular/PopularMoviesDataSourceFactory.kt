package com.example.home.popular

import androidx.paging.DataSource
import com.example.commonui.MovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetPopularMovieListUseCase
import kotlinx.coroutines.CoroutineScope

class PopularMoviesDataSourceFactory(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieModel>() {

    var popularMoviesDataSource: PopularMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieModel> {

        popularMoviesDataSource = PopularMoviesDataSource(
            paginationStatus,
            getPopularMovieListUseCase,
            scope
        )

        return popularMoviesDataSource as PopularMoviesDataSource
    }
}
