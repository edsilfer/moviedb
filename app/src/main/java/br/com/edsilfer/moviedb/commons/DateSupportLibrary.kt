package br.com.edsilfer.moviedb.commons

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

fun Date.format(pattern: String): String {
    var formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}
