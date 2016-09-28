package br.com.edsilfer.moviedb.service.comm

import br.com.edsilfer.moviedb.infrastructure.App
import javax.inject.Inject

/**
 * Delegates the application calls to the corresponding executors
 */
class Postman(var mRequestService: RestTemplate) {

    @Inject
    lateinit var mMovieOffice: MovieOffice

    // LIFECYCLE ===================================================================================
    init {
        App.component!!.inject(this)
    }

    // PUBLIC INTERFACE ============================================================================
    fun cancelRequests() {
        mRequestService.cancelRequests()
    }

    // MOVIE OFFICE --------------------------------------------------------------------------------
    fun listMovies() {
        mMovieOffice.list()
    }

    fun searchMovie(query: String) {
        mMovieOffice.search(query)
    }
}
