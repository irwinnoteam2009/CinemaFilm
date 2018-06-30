package ru.meteoctx.hellboys.cinemafilm.data.repository

import io.reactivex.Single
import ru.meteoctx.hellboys.cinemafilm.data.model.MovieMapper
import ru.meteoctx.hellboys.cinemafilm.data.remote.MovieApi
import ru.meteoctx.hellboys.cinemafilm.data.remote.response.MovieListResponse
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie
import ru.meteoctx.hellboys.cinemafilm.domain.repository.IMovieRepository

class MovieRepository(
        private val api: MovieApi,
        private val mapper: MovieMapper): IMovieRepository {


    override fun getMovieList(page: Int): Single<List<Movie>> {

        return api.getMovies(page)
                .flatMap { response: MovieListResponse ->
                    Single.just(response.results)
                }
                .map { list -> mapper.mapTo(list) }
    }
}