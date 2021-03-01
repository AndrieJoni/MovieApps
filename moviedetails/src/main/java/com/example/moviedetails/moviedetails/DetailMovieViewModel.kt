package com.example.moviedetails.moviedetails

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.commonui.MovieModel
import com.example.commonui.toMovie
import com.example.moviedetails.moviedetails.adapter.ReviewsMovieDataSourceFactory
import com.example.navigation.Navigation
import com.example.util.SingleLiveEvent
import com.movie.domain.usecase.GetMovieReviewsUseCase
import com.movie.domain.usecase.IsMovieMarkedAsFavoriteUseCase
import com.movie.domain.usecase.MarkedMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val markedMovieUseCase: MarkedMovieUseCase,
    private val isMovieMarkedAsFavoriteUseCase: IsMovieMarkedAsFavoriteUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieData: MovieModel? = null
    val showMovieData = MutableLiveData<MovieModel>()
    val saveFavoriteMovieEvent = SingleLiveEvent<Boolean>()
    val changeFavoriteIconColorEvent = SingleLiveEvent<Boolean>()
    val shareLinkEvent = SingleLiveEvent<String>()

    private var isFavorite = false

    var reviewsMovieDataSourceFactory: ReviewsMovieDataSourceFactory? = null

    init {
        movieData = savedStateHandle.get<MovieModel>(Navigation.MOVIE_DATA)
        getMovieData()
    }

    private fun initializePageConfig(): PagedList.Config {

        return PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(15)
            .build()
    }

    fun initializeReviewsMovieLiveData(): LiveData<PagedList<MovieReviewModel>> {

        reviewsMovieDataSourceFactory = ReviewsMovieDataSourceFactory(
            movieData?.id.toString(),
            getMovieReviewsUseCase,
            viewModelScope
        )

        return LivePagedListBuilder(
            reviewsMovieDataSourceFactory!!,
            initializePageConfig()
        ).build()
    }

    private fun getMovieData() {

        if (movieData != null) showMovieData.value = movieData!!

        viewModelScope.launch(Dispatchers.IO) {

            if (movieData != null)
                isFavorite = isMovieMarkedAsFavoriteUseCase.invoke(movieData!!.toMovie())

            withContext(Dispatchers.Main) {
                changeFavoriteIconColorEvent.value = isFavorite
            }
        }
    }

    fun favoriteIconClicked() {

        viewModelScope.launch(Dispatchers.IO) {

            if (movieData != null) {

                isFavorite = markedMovieUseCase.invoke(movieData!!.toMovie())

                withContext(Dispatchers.Main) {
                    saveFavoriteMovieEvent.value = isFavorite
                    changeFavoriteIconColorEvent.value = isFavorite
                }
            }
        }
    }

    fun shareIconClicked() {
        shareLinkEvent.value = movieData?.title.toString()
    }
}