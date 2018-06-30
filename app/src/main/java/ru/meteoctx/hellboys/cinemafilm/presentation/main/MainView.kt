package ru.meteoctx.hellboys.cinemafilm.presentation.main

import com.arellomobile.mvp.MvpView

interface MainView: MvpView {
    fun startLoading()
    fun stopLoading()

    fun showError(text: String)
    fun showError(t: Throwable) {
        showError(t.message ?: t.printStackTrace().toString())
    }
}