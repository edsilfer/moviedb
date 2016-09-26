package br.com.edsilfer.bidder.util

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.edsilfer.moviedb.R
import com.squareup.picasso.Picasso
import com.wang.avi.AVLoadingIndicatorView
import org.jetbrains.anko.find

/* Android v4 support library */

fun View.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar {
    val snack = Snackbar.make(this, text, duration)
    snack.init()
    snack.show()
    return snack
}

fun View.snackbar(text: Int, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar {
    val snack = Snackbar.make(this, text, duration)
    snack.init()
    snack.show()
    return snack
}

fun Fragment.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar =
        view!!.snackbar(text, duration, init)

fun Fragment.snackbar(text: Int, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar =
        view!!.snackbar(text, duration, init)

fun Activity.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar =
        find<View>(R.id.content).snackbar(text, duration, init)

fun Activity.snackbar(text: Int, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar =
        find<View>(R.id.content).snackbar(text, duration, init)

fun Activity.customSnackbar(
        message: String,
        ic: Int = -1,
        showLoading: Boolean = false,
        showAction: Boolean = false,
        actionLabel: String = "",
        callback: () -> Unit = {},
        duration: Int = Snackbar.LENGTH_SHORT) {
    runOnUiThread {
        try {
            val customView = layoutInflater.inflate(R.layout.rsc_util_custom_snackbar, null)
            val content = customView.findViewById(R.id.content) as TextView
            val icon = customView.findViewById(R.id.icon) as ImageView
            val loading = customView.findViewById(R.id.loading) as AVLoadingIndicatorView
            val action = customView.findViewById(R.id.action) as TextView

            val snackbar = Snackbar.make(window.decorView.findViewById(android.R.id.content), "", duration)
            val layout = snackbar.view as Snackbar.SnackbarLayout

            content.setText(message)
            if (ic != -1) Picasso.with(this).load(ic).into(icon)
            if (showLoading) loading.visibility = AVLoadingIndicatorView.VISIBLE
            else loading.visibility = AVLoadingIndicatorView.GONE

            if (showAction) {
                action.text = actionLabel
                action.visibility = TextView.VISIBLE
                action.setOnClickListener {
                    callback()
                }
            } else {
                action.visibility = TextView.GONE
            }

            layout.addView(customView, 0)
            snackbar.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}