package ru.meteoctx.hellboys.cinemafilm.presentation.movie.list

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.meteoctx.hellboys.cinemafilm.domain.interactor.IMoviesInteractor
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

@InjectViewState
class MovieListPresenter(private val kodein: Kodein): MvpPresenter<MovieListView>() {
    private val compositeDisposable = CompositeDisposable()
    private val interactor: IMoviesInteractor by kodein.lazy.instance()
    private val TAG = "MovieList"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.wtf(TAG, "first attach")
        loadMovies()
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.clear()
        Log.wtf(TAG, "destroy")
        super.onDestroy()
    }

    override fun destroyView(view: MovieListView?) {
        super.destroyView(view)
        Log.wtf(TAG, "destroy view")
    }

    override fun attachView(view: MovieListView?) {
        super.attachView(view)
        Log.wtf(TAG, "attach")
    }

    override fun detachView(view: MovieListView?) {
        super.detachView(view)
        Log.wtf(TAG, "detach")
    }

    private fun loadMovies() {
        compositeDisposable.add(interactor.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            viewState.stopLoading()
                            viewState.showMovies(it)
                            if (it.isEmpty()) viewState.showNoContent()
                        },
                        {
                            viewState.stopLoading()
                            viewState.showError(it)
                        }
                )
        )
    }
}