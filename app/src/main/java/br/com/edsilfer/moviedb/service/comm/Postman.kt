package br.com.edsilfer.moviedb.service.comm

import android.graphics.Movie
import br.com.edsilfer.moviedb.infrastructure.App
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */
class Postman(var mRequestService: RestTemplate) {

    @Inject
    lateinit var mMovieOffice: MovieOffice

    init {
        App.component!!.inject(this)
    }

    fun cancelRequests() {
        mRequestService.cancelRequests()
    }

    // MOVIE OFFICE ================================================================================
    fun listMovies() {
        mMovieOffice.list()
    }

    fun searchMovie(query: String) {
        mMovieOffice.search(query)
    }
}
