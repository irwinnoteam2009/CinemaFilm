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
    private lateinit var moviesFragment: MovieListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         if (savedInstanceState == null) {
            moviesFragment = MovieListFragment.newInstance {
                val infoFragment = MovieInfoFragment.newInstance(0, it)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, infoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.container, moviesFragment, MovieListFragment.TAG)
                    .commit()
        } else
             moviesFragment = supportFragmentManager.findFragmentByTag(MovieListFragment.TAG) as MovieListFragment


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}