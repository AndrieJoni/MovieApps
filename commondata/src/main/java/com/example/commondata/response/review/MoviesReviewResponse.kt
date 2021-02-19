package com.example.commondata.response.review

import com.google.gson.annotations.SerializedName

class MoviesReviewResponse {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("results")
    var listOfMoviesReviews: ArrayList<MovieReviewDto> = arrayListOf()
}