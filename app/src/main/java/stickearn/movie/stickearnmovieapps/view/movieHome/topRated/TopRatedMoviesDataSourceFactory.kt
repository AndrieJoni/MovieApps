package stickearn.movie.stickearnmovieapps.view.movieHome.topRated

import androidx.paging.DataSource
import com.movie.domain.usecase.GetTopRatedMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import stickearn.movie.stickearnmovieapps.data.MovieData
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.PaginationStatus
import stickearn.movie.stickearnmovieapps.view.movieHome.MovieHomeModel

class TopRatedMoviesDataSourceFactory(
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieHomeModel>() {

    var topRatedMoviesDataSource: TopRatedMoviesDataSource? = null
    val paginationStatus = SingleLiveEvent<PaginationStatus>()

    override fun create(): DataSource<Int, MovieHomeModel> {

        topRatedMoviesDataSource = TopRatedMoviesDataSource(
            paginationStatus,
            getTopRatedMovieListUseCase,
            scope
        )

        return topRatedMoviesDataSource as TopRatedMoviesDataSource
    }
}
