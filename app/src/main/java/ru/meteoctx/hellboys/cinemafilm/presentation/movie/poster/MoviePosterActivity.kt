package ru.meteoctx.hellboys.cinemafilm.presentation.movie.poster

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_poster.*
import ru.meteoctx.hellboys.cinemafilm.R

class MoviePosterActivity: MvpAppCompatActivity(), MoviePosterView {

    companion object {
        const val POSTER_URL = "url"

        fun start(context: Context, url: String?) {
            if (url.isNullOrEmpty()) return
            val intent = Intent(context, MoviePosterActivity::class.java)
            intent.putExtra(POSTER_URL, url)
            context.startActivity(intent)
        }
    }
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        url = intent.getStringExtra(POSTER_URL)
        Glide.with(this).load(url).into(poster)
    }
}