package ru.meteoctx.hellboys.cinemafilm.domain.repository

import io.reactivex.Single
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

interface IMovieRepository {
    fun getMovieList(page: Int): Single<List<Movie>>
}