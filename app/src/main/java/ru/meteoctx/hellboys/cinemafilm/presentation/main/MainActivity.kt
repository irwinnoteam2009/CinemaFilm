package ru.meteoctx.hellboys.cinemafilm.presentation.main

import android.app.FragmentTransaction
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.presentation.movie.info.MovieInfoFragment
import ru.meteoctx.hellboys.cinemafilm.presentation.movie.list.MovieListFragment

class MainActivity: MvpAppCompatActivity(), MainView {

    @InjectPresenter lateinit var presenter: MainPresenter
    private var moviesFragment: MovieListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesFragment = supportFragmentManager.findFragmentByTag(MovieListFragment.TAG) as MovieListFragment?
        if (moviesFragment == null) {
            moviesFragment = MovieListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, moviesFragment, MovieListFragment.TAG)
                    .commit()
        }

       moviesFragment?.setMovieSelectListener {
            val infoFragment = MovieInfoFragment.newInstance(it)
            supportFragmentManager.beginTransaction()
                    .hide(moviesFragment)
                    .add(R.id.container, infoFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
        }
    }
}