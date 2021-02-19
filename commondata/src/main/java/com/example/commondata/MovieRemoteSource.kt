package com.example.commondata

import com.example.commondata.response.movie.MovieListResponse
import com.example.commondata.response.review.MoviesReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteSource {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: String
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: String
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: String
    ): MovieListResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: String,
        @Query("page") page: String
    ): MoviesReviewResponse

}