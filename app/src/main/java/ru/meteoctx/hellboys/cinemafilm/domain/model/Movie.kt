package ru.meteoctx.hellboys.cinemafilm.domain.model

import java.io.Serializable

data class Genre(val name: String): Serializable

data class Movie (
        val adult: Boolean? = false,
        val genres: List<Genre> = arrayListOf(),
        val id: Int,
        val originalLang: String? = null,
        val originalTitle: String? = null,
        val overview: String? = null,
        val releaseAt: String? = null,
        val posterPath: String? = null,
        val popularity: Float? = null,
        val title: String,
        val video: String? = null,
        val voteAverage: Float? = null,
        val voteCount: Int? = null
): Serializable