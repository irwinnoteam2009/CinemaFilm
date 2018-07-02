package ru.meteoctx.hellboys.cinemafilm.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreEntity(val name: String)

data class MovieEntity (
        val adult: Boolean? = false,
        val genres: List<GenreEntity>,
        val id: Int,
        @SerializedName("original_language")
        val originalLang: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        val overview: String?,
        @SerializedName("release_date")
        val releaseAt: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        val popularity: Float?,
        val title: String,
        val video: String?,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("vote_count")
        val voteCount: Int?
)