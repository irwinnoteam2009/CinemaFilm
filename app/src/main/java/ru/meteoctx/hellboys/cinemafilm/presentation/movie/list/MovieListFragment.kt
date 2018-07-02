package ru.meteoctx.hellboys.cinemafilm.presentation.movie.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.di.diKodein
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie
import ru.meteoctx.hellboys.cinemafilm.presentation.common.initWithAdapter

class MovieListFragment: MvpAppCompatFragment(), MovieListView {

    companion object {
        const val TAG = "MovieList"
    }

    @InjectPresenter lateinit var presenter: MovieListPresenter
    @ProvidePresenter fun provideMainPresenter(): MovieListPresenter = MovieListPresenter(diKodein)

    private var adapter: MovieListAdapter = MovieListAdapter()
    private var onMovieSelectedListener: MovieListAdapter.OnMovieSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setMovieSelectedListener(onMovieSelectedListener)
        movies.initWithAdapter(context!!, adapter)
    }

    fun setMovieSelectListener(action: (movie: Movie) -> Unit) {
        onMovieSelectedListener = object : MovieListAdapter.OnMovieSelectedListener {
            override fun onSelect(movie: Movie) {
                action(movie)
            }
        }
    }

    override fun startLoading() {

    }

    override fun stopLoading() {

    }

    override fun showError(text: String) {
        Snackbar.make(view!!, text, Snackbar.LENGTH_LONG).show()
    }

    override fun showMovies(list: List<Movie>) {
        movies.visibility = View.VISIBLE
        adapter.setMovies(list)
    }

    override fun showNoContent() {
        movies.visibility = View.GONE
    }
}