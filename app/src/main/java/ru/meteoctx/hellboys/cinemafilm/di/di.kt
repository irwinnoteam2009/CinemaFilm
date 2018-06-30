package ru.meteoctx.hellboys.cinemafilm.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import ru.meteoctx.hellboys.cinemafilm.data.model.GenreMapper
import ru.meteoctx.hellboys.cinemafilm.data.model.MovieMapper
import ru.meteoctx.hellboys.cinemafilm.data.remote.MovieApi
import ru.meteoctx.hellboys.cinemafilm.data.remote.MovieApiFactory
import ru.meteoctx.hellboys.cinemafilm.data.repository.MovieRepository
import ru.meteoctx.hellboys.cinemafilm.domain.interactor.IMoviesInteractor
import ru.meteoctx.hellboys.cinemafilm.domain.interactor.MoviesInteractor
import ru.meteoctx.hellboys.cinemafilm.domain.repository.IMovieRepository

val diKodein = Kodein {
    bind<GenreMapper>() with singleton { GenreMapper() }
    bind<MovieMapper>() with singleton { MovieMapper(instance()) }

    bind<MovieApi>() with  singleton { MovieApiFactory.makeMovieApi() }
    bind<IMovieRepository>() with singleton { MovieRepository(instance(), instance()) }
    bind<IMoviesInteractor>() with singleton { MoviesInteractor(instance()) }


}