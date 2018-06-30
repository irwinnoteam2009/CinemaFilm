package ru.meteoctx.hellboys.cinemafilm.presentation.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.di.diKodein


class MainActivity: MvpAppCompatActivity(), MainView {
    @InjectPresenter lateinit var presenter: MainPresenter

    @ProvidePresenter fun provideMainPresenter(): MainPresenter = MainPresenter(diKodein)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun startLoading() {

    }

    override fun stopLoading() {

    }

    override fun showError(text: String) {
        Snackbar.make(findViewById(R.id.main_activity), text, Snackbar.LENGTH_LONG).show()
    }
}