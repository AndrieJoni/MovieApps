package stickearn.movie.stickearnmovieapps.view.movieHome.popular

import androidx.paging.PageKeyedDataSource
import com.movie.domain.usecase.GetPopularMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import stickearn.movie.stickearnmovieapps.utils.SingleLiveEvent
import stickearn.movie.stickearnmovieapps.view.PaginationStatus
import stickearn.movie.stickearnmovieapps.view.movieHome.MovieHomeModel
import stickearn.movie.stickearnmovieapps.view.toMovieHomeModel

class PopularMoviesDataSource(
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val scope: CoroutineScope

) : PageKeyedDataSource<Int, MovieHomeModel>() {

    private suspend fun getPopularMovieData(page: String) =
        getPopularMovieListUseCase.invoke(page)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieHomeModel>
    ) {

        scope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    getPopularMovieData("1")
                }

                if (response.isEmpty()) {
                    paginationStatus.postValue(PaginationStatus.Empty)
                } else {
                    callback.onResult(response.map { it.toMovieHomeModel() }, null, 2)
                }


            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieHomeModel>) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getPopularMovieData(params.key.toString())

                withContext(Dispatchers.Main) {
                    callback.onResult(response.map { it.toMovieHomeModel() }, params.key + 1)
                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieHomeModel>) {
    }
}