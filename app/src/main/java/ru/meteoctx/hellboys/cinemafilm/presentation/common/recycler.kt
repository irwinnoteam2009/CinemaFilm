package ru.meteoctx.hellboys.cinemafilm.presentation.common

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.initWithAdapter(context: Context, adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.adapter = adapter
    this.setHasFixedSize(true)
    val divider = DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL)
    this.addItemDecoration(divider)
}