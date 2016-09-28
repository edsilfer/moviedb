package br.com.edsilfer.bidder.util

import android.text.TextUtils
import android.widget.TextView

/**
 * ANKO Support library for Any class. It extends the base class offering common services that allow
 * s the programmer to produce a cleaner code
 */
fun TextView.slideHorizontal() {
    ellipsize = TextUtils.TruncateAt.MARQUEE
    marqueeRepeatLimit = -1
    isSelected = true
}