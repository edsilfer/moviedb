package br.com.edsilfer.moviedb.commons

import java.text.SimpleDateFormat
import java.util.*

/**
 * ANKO Support library for Any class. It extends the base class offering common services that allow
 * s the programmer to produce a cleaner code
 */
fun Date.format(pattern: String): String {
    var formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}
