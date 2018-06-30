package ru.meteoctx.hellboys.cinemafilm.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import io.reactivex.disposables.CompositeDisposable
import ru.meteoctx.hellboys.cinemafilm.domain.interactor.IMoviesInteractor

@InjectViewState
class MainPresenter(private val kodein: Kodein): MvpPresenter<MainView>() {

    private val compositeDisposable = CompositeDisposable()

    private val interactor: IMoviesInteractor by kodein.lazy.instance()

    override fun attachView(view: MainView?) {
        super.attachView(view)

        compositeDisposable.add(interactor.getMovies().subscribe(
                {
                    viewState.stopLoading()
                },
                {
                    viewState.stopLoading()
                    viewState.showError(it)
                }
        )
        )
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.clear()
        super.onDestroy()
    }
}