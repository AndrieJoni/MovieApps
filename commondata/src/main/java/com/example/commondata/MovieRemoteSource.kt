package com.example.commondata

import retrofit2.http.GET
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
}