package ru.meteoctx.hellboys.cinemafilm.presentation.movie.info

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MovieInfoPresenter: MvpPresenter<MovieInfoView>() {

    private val TAG = "MovieInfo"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.wtf(TAG, "first attach")
    }

    override fun attachView(view: MovieInfoView?) {
        super.attachView(view)
        Log.wtf(TAG, "attach")
    }

    override fun detachView(view: MovieInfoView?) {
        super.detachView(view)
        Log.wtf(TAG, "detach")
    }

    override fun onDestroy() {
        Log.wtf(TAG, "destroy")
        super.onDestroy()
    }
}