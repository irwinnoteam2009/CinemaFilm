package ru.meteoctx.hellboys.cinemafilm.presentation.movie.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.meteoctx.hellboys.cinemafilm.domain.interactor.IMoviesInteractor

@InjectViewState
class MovieListPresenter(private val kodein: Kodein): MvpPresenter<MovieListView>() {
    private val compositeDisposable = CompositeDisposable()
    private val interactor: IMoviesInteractor by kodein.lazy.instance()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadMovies()
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.clear()
        super.onDestroy()
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