package ru.meteoctx.hellboys.cinemafilm.presentation.movie.list

import com.arellomobile.mvp.MvpView
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

interface MovieListView: MvpView {
    fun startLoading()
    fun stopLoading()

    fun showError(text: String)
    fun showError(t: Throwable) {
        showError(t.message ?: t.printStackTrace().toString())
    }

    fun showNoContent()
    fun showMovies(list: List<Movie>)
}