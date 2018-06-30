package ru.meteoctx.hellboys.cinemafilm.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.meteoctx.hellboys.cinemafilm.data.model.MovieEntity

data class MovieListResponse(
        val page: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        val results: List<MovieEntity>?
)