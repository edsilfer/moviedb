package br.com.edsilfer.moviedb.service

import android.graphics.Movie
import br.com.edsilfer.moviedb.infrastructure.App
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class Postman {

    @Inject
    lateinit var mMovieOffice: MovieOffice

    init {
        App.component!!.inject(this)
    }

    // MOVIE OFFICE ================================================================================
    fun listMovies() {
        mMovieOffice.listMovies()
    }

}
