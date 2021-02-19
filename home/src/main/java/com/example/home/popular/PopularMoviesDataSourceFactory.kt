package com.example.home.popular

import androidx.paging.DataSource
import com.example.home.MovieHomeModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetPopularMovieListUseCase
import kotlinx.coroutines.CoroutineScope

class PopularMoviesDataSourceFactory(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieHomeModel>() {

    var popularMoviesDataSource: PopularMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieHomeModel> {

        popularMoviesDataSource = PopularMoviesDataSource(
            paginationStatus,
            getPopularMovieListUseCase,
            scope
        )

        return popularMoviesDataSource as PopularMoviesDataSource
    }
}
