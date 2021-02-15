package com.example.commondata

import com.google.gson.annotations.SerializedName

class MovieListResponse {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("dates")
    var dates: Dates = Dates()

    @SerializedName("results")
    var listOfMovies: List<MovieDto> = arrayListOf()

    class Dates {

        var maximum: String = ""
        var minimum: String = ""
    }
}