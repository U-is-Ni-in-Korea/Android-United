package sopt.uni_aos.util.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, message: String, isShort: Boolean = true) {
    val duration = if (isShort) {
        Snackbar.LENGTH_SHORT
    } else {
        Snackbar.LENGTH_LONG
    }
    Snackbar.make(view, message, duration).show()
}