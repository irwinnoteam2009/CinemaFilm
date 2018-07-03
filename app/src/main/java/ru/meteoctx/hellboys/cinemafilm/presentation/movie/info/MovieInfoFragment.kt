package ru.meteoctx.hellboys.cinemafilm.presentation.movie.info

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_movie_info.*
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie
import ru.meteoctx.hellboys.cinemafilm.presentation.common.bundle
import ru.meteoctx.hellboys.cinemafilm.presentation.common.initWithAdapter

class MovieInfoFragment: MvpAppCompatFragment(), MovieInfoView {

    companion object {
        const val TAG = "MovieInfo"

        fun newInstance(movie: Movie): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            val bundle = bundle {
                putSerializable("movie", movie)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    @InjectPresenter lateinit var presenter: MovieInfoPresenter
    private val adapter: InfoAdapter = InfoAdapter()
    private var player: SimpleExoPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie? = arguments?.getSerializable("movie") as Movie?
        movie ?: return

        title.text = movie.title
        original_name.text = movie.originalTitle
        Glide.with(this).load(movie.posterPath).into(poster)
        overview.text = movie.overview
        info.initWithAdapter(context!!, adapter)

        adapter.setField("Release", movie.releaseAt ?: "")
        adapter.setField("Popularity", movie.popularity?.toString() ?: "0")
        adapter.setField("Vote", "${movie.voteAverage?.toString()} (${movie.voteCount?.toString()})" ?: "0 (0)")

        val position: Long = savedInstanceState?.getLong("position") ?: 0
        initPlayer(movie.video, position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("position", player?.currentPosition ?: 0)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        player?.release()
        super.onPause()
    }

    private fun initPlayer(url: String?, position: Long) {
        url ?: return

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        player  = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        player_view.player = player
        player?.playWhenReady = true

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "cinemaFilm"))
        val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url))

        player?.prepare(videoSource)
        player?.seekTo(position)
    }
}