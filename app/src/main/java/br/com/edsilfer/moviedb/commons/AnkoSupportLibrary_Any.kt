package br.com.edsilfer.moviedb.commons

import android.util.Log
import br.com.edsilfer.bidder.util.LogLevel

/**
 * ANKO Support library for Any class. It extends the base class offering common services that allow
 * s the programmer to produce a cleaner code
 */
// ENUNS ===========================================================================================
fun Any.log(content: String, level: LogLevel = LogLevel.WARNING) {
    when (level) {
        LogLevel.ERROR -> Log.e("TAG", content)
        LogLevel.WARNING -> Log.i("TAG", content)
    }
}