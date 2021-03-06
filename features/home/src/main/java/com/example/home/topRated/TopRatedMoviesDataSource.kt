package com.example.home.topRated

import androidx.paging.PageKeyedDataSource
import com.example.commonui.MovieModel
import com.example.commonui.toMovieModel
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetTopRatedMovieListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopRatedMoviesDataSource(
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val scope: CoroutineScope

) : PageKeyedDataSource<Int, MovieModel>() {

    private suspend fun getTopRatedMovieData(page: String) =
        getTopRatedMovieListUseCase.invoke(page)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getTopRatedMovieData("1")

                withContext(Dispatchers.Main) {

                    if (response.isEmpty()) {
                        paginationStatus.postValue(PaginationStatus.Empty)
                    } else {
                        callback.onResult(response.map { it.toMovieModel() }, null, 2)
                    }

                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getTopRatedMovieData(params.key.toString())

                withContext(Dispatchers.Main) {
                    callback.onResult(response.map { it.toMovieModel() }, params.key + 1)
                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
    }
}