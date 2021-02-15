package stickearn.movie.stickearnmovieapps.view.movieHome.popular

import androidx.paging.DataSource
import com.movie.domain.usecase.GetPopularMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import stickearn.movie.stickearnmovieapps.data.MovieData
import stickearn.movie.stickearnmovieapps.view.PaginationStatus
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.movieHome.MovieHomeModel

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
