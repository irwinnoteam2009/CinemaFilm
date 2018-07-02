package ru.meteoctx.hellboys.cinemafilm.presentation.common

import android.os.Bundle

fun bundle(b: Bundle.()  -> Unit): Bundle {
    val res = Bundle()
    b(res)
    return res
}