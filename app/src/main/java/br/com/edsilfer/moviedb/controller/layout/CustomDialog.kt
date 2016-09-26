package br.com.gogame.view.layouts

import android.app.Dialog
import android.content.Context

/**
 * Created by r720929 on 28/07/2016.
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