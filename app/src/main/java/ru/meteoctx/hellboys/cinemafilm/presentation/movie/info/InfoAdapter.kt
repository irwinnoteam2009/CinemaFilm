package ru.meteoctx.hellboys.cinemafilm.presentation.movie.info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.meteoctx.hellboys.cinemafilm.R

class InfoAdapter: RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    private val fields = linkedMapOf(
            "Release" to "1999-12-10",
            "Popularity" to "0",
            "Vote" to "0 (0)"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_info, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fields.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = fields.keys.elementAt(position)
        holder.bind(key)
    }

    fun setField(key: String, value: String) {
        var index = fields.keys.indexOf(key)
        fields[key] = value
        if (index == -1) {
           index = fields.keys.indexOf(key)
            notifyItemInserted(index)
            return
        }

        notifyItemChanged(index)
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        private val key: TextView = view.findViewById(R.id.key)
        private val value: TextView = view.findViewById(R.id.value)

        fun bind(field: String) {
            key.text = field
            value.text = fields[field] ?: ""
        }
    }
}