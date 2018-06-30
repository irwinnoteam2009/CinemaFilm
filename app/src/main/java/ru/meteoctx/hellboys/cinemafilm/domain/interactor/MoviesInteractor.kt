package ru.meteoctx.hellboys.cinemafilm.domain.interactor

import io.reactivex.Single
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie
import ru.meteoctx.hellboys.cinemafilm.domain.repository.IMovieRepository

class MoviesInteractor(private val repository: IMovieRepository): IMoviesInteractor {
    override fun getMovies(page: Int?): Single<List<Movie>> = repository.getMovieList(page ?: 1)
}