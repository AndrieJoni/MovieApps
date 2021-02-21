package com.example.moviedetails.moviedetails.adapter

import androidx.paging.PageKeyedDataSource
import com.example.util.PaginationStatus
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetMovieReviewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.moviedetails.moviedetails.MovieReviewModel
import com.example.moviedetails.moviedetails.toMovieReviewModel

class ReviewsMovieDataSource(
    private val id: String,
    private val paginationStatus: SingleLiveEvent<PaginationStatus>,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, MovieReviewModel>() {

    private suspend fun getMovieReviewsData(movieId: String, page: String) =
        getMovieReviewsUseCase.invoke(
            mapOf(
                "movieId" to movieId,
                "page" to page
            )
        )

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieReviewModel>
    ) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getMovieReviewsData(id, "1")

                withContext(Dispatchers.Main) {

                    if (response.isEmpty()) {
                        paginationStatus.postValue(PaginationStatus.Empty)
                    } else {
                        callback.onResult(response.map { it.toMovieReviewModel() }, null, 2)
                    }
                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieReviewModel>) {

        scope.launch(Dispatchers.IO) {

            try {

                val response = getMovieReviewsData(id, params.key.toString())

                withContext(Dispatchers.Main) {
                    callback.onResult(response.map { it.toMovieReviewModel() }, params.key + 1)
                }

            } catch (e: Exception) {
                paginationStatus.postValue(PaginationStatus.Error)
                e.printStackTrace()
                return@launch
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieReviewModel>
    ) {
    }
}