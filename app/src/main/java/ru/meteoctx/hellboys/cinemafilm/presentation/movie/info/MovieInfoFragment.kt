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
import ru.meteoctx.hellboys.cinemafilm.presentation.movie.poster.MoviePosterActivity

class MovieInfoFragment: MvpAppCompatFragment(), MovieInfoView {

    companion object {
        const val TAG = "MovieInfoFragment"

        fun newInstance(movie: Movie): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            val bundle = bundle {
                putSerializable("movie", movie)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private val PLAYER_POSITION = "position"
    private val PLAYER_PLAY = "play"

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
        poster.setOnClickListener {
            MoviePosterActivity.start(context!!, movie.posterPath)
        }
        Glide.with(this).load(movie.posterPath).into(poster)
        overview.text = movie.overview
        info.initWithAdapter(context!!, adapter)

        adapter.setField("Release", movie.releaseAt ?: "")
        adapter.setField("Popularity", movie.popularity?.toString() ?: "0")
        adapter.setField("Vote", "${movie.voteAverage?.toString() ?: 0} (${movie.voteCount?.toString() ?: 0})")
        val sb = StringBuilder()
        movie.genres.forEachIndexed { index, genre ->
            if (sb.isNotEmpty()) sb.append(", ")
            sb.append(genre.name)
        }
        adapter.setField("Genre: ", sb.toString())

        val position: Long = savedInstanceState?.getLong(PLAYER_POSITION) ?: 0
        val play: Boolean = savedInstanceState?.getBoolean(PLAYER_PLAY) ?: true
        initPlayer(movie.video, position, play)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(PLAYER_POSITION, player?.currentPosition ?: 0)
        outState.putBoolean(PLAYER_PLAY, player?.playWhenReady ?: true)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        player?.release()
        super.onPause()
    }

    private fun initPlayer(url: String?, position: Long, play: Boolean) {
        url ?: return

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        player  = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        player_view.player = player
        player?.playWhenReady = play

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "cinemaFilm"))
        val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url))

        player?.prepare(videoSource)
        player?.seekTo(position)
    }
}