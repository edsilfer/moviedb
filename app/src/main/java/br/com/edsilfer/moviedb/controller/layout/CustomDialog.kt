package br.com.gogame.view.layouts

import android.app.Dialog
import android.content.Context

/**
 * Util class responsible for handling the size of the recently opened Dialog
 */
class CustomDialog(context: Context, val callback: (height: Int, width: Int) -> Unit) : Dialog(context) {

    var mCallEvent = true

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (mCallEvent) {
            mCallEvent = false
            callback(window.decorView.height, window.decorView.width)
        }
    }
}