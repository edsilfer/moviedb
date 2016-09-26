package br.com.edsilfer.bidder.util

import android.text.TextUtils
import android.widget.TextView

/**
 * Created by r720929 on 01/08/2016.
 */
fun TextView.slideHorizontal() {
    ellipsize = TextUtils.TruncateAt.MARQUEE
    marqueeRepeatLimit = -1
    isSelected = true
}