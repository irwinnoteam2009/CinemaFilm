package ru.meteoctx.hellboys.cinemafilm.domain.interactor


import io.reactivex.Single
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

interface IMoviesInteractor {
    fun getMovies(page: Int? = null): Single<List<Movie>>
}