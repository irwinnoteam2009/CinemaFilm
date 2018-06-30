package ru.meteoctx.hellboys.cinemafilm.data.model

import ru.meteoctx.hellboys.cinemafilm.data.Mapper
import ru.meteoctx.hellboys.cinemafilm.domain.model.Genre
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

class GenreMapper: Mapper<GenreEntity, Genre> {
    // to domain
    override fun mapTo(from: GenreEntity) = Genre(from.name)
    // from domain
    override fun mapFrom(entity: Genre) = GenreEntity(entity.name)
}

class MovieMapper(private val mapper: GenreMapper): Mapper<MovieEntity, Movie> {
    // to domain
    override fun mapTo(from: MovieEntity) = Movie(
            adult = from.adult,
            genres = mapper.mapTo(from.genres),
            id = from.id,
            originalLang = from.originalLang,
            originalTitle = from.originalTitle,
            overview = from.overview,
            releaseAt = from.releaseAt,
            posterPath = from.posterPath,
            popularity = from.popularity,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount
    )

    // from domain
    override fun mapFrom(entity: Movie) = MovieEntity(
            adult = entity.adult,
            genres = mapper.mapFrom(entity.genres),
            id = entity.id,
            originalLang = entity.originalLang,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            releaseAt = entity.releaseAt,
            posterPath = entity.posterPath,
            popularity = entity.popularity,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
    )
}