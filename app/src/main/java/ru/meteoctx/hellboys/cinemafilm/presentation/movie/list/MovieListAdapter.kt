package ru.meteoctx.hellboys.cinemafilm.presentation.movie.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.meteoctx.hellboys.cinemafilm.R
import ru.meteoctx.hellboys.cinemafilm.domain.model.Movie

class MovieListAdapter(private val onItemClick: (movie: Movie) -> Unit = {}): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun setMovies(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun getMovies(): List<Movie> = movies

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.title)
        private val poster: ImageView = view.findViewById(R.id.poster)

        fun bind(movie: Movie) {
            title.text = movie.title
            Glide.with(view)
                    .load(movie.posterPath)
                    .into(poster)

            view.setOnClickListener { onItemClick(movie) }
        }
    }
}