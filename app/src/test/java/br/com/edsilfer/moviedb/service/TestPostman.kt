package br.com.edsilfer.moviedb.service

import android.util.Log

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

import br.com.edsilfer.moviedb.service.comm.MovieOffice
import br.com.edsilfer.moviedb.service.comm.Postman
import br.com.edsilfer.moviedb.service.comm.RestTemplate

import org.mockito.Mockito.times
import org.mockito.Mockito.verify

/**
 * Created by User on 29/09/2016.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class TestPostman {

    @Mock
    internal var mRequestService: RestTemplate? = null
    @Mock
    internal var mMovieOffice: MovieOffice? = null

    @InjectMocks
    internal var mPostman: Postman? = null

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
    }

    @Test
    fun testListMovies() {
        mPostman!!.listMovies()
        verify<MovieOffice>(mMovieOffice, times(1)).list()
    }

    @Test
    fun testSearchMovies() {
        val query = "query"
        mPostman!!.searchMovie(query)
        verify<MovieOffice>(mMovieOffice, times(1)).search(query)
    }

    @Test
    fun testCancelRequests() {
        mPostman!!.cancelRequests()
        verify<RestTemplate>(mRequestService, times(1)).cancelRequests()
    }
}
