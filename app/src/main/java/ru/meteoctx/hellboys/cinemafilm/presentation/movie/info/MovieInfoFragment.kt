package ru.meteoctx.hellboys.cinemafilm.presentation.movie.info

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_info.*
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie
import ru.meteoctx.hellboys.cinemafilm.presentation.common.bundle
import ru.meteoctx.hellboys.cinemafilm.presentation.common.initWithAdapter

class MovieInfoFragment: MvpAppCompatFragment(), MovieInfoView {
    companion object {
        const val TAG = "MovieInfo"

        fun newInstance(id: Int, movie: Movie): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            val args = bundle {
                putInt("ID", id)
                putSerializable("movie", movie)
            }
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter lateinit var presenter: MovieInfoPresenter
    private val adapter = InfoAdapter()
    private val movie: Movie by lazy { arguments?.getSerializable("movie") as Movie }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = movie.title
        original_name.text = movie.originalTitle
        Glide.with(view).load(movie.posterPath).into(poster)
        overview.text = movie.overview
        info.initWithAdapter(context!!, adapter)

        val mediaController = MediaController(view.context)
        mediaController.setAnchorView(view)
        video.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4")
        video.setMediaController(mediaController)
        video.requestFocus()
        video.setOnPreparedListener {
            video.start()
        }

        adapter.setField("Release", movie.releaseAt ?: "")
        adapter.setField("Popularity", movie.popularity?.toString() ?: "0")
        adapter.setField("Vote", "${movie.voteAverage?.toString()} (${movie.voteCount?.toString()})" ?: "0 (0)")
    }


}