package com.ramadan.coffeeshifts.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ramadan.coffeeshifts.ui.MainActivity

fun Fragment.showLoading() {
    (this.activity as MainActivity).binding.progressBar.visibility = View.VISIBLE
}

fun Fragment.hideLoading() {
    (this.activity as MainActivity).binding.progressBar.visibility = View.GONE
}

fun Fragment.showSnackMessage(message: String) {
    val mainView = (this.activity as MainActivity).binding.mainView
    Snackbar.make(mainView, message, Snackbar.LENGTH_SHORT).show()
}
