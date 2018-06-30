package ru.meteoctx.hellboys.cinemafilm.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.meteoctx.hellboys.cinemafilm.data.remote.response.MovieListResponse

interface MovieApi {
    @GET("numbata/5ed307d7953c3f7e716f/raw/b7887adc444188d8aa8e61d39b82950f28c03966/movies.json")
    fun getMovies(@Query("page") page: Int?): Single<MovieListResponse>
}