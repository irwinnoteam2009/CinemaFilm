package ru.meteoctx.hellboys.cinemafilm.presentation.common

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, text, duration).show()