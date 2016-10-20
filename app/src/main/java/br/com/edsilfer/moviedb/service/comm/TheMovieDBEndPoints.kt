package br.com.edsilfer.moviedb.service.comm

import br.com.edsilfer.moviedb.model.ListMoviesResponseWrapper
import br.com.edsilfer.moviedb.model.SearchMoviesResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by User on 19/10/2016.
 */

interface TheMovieDBEndPoints {

    @GET("movie/upcoming/")
    fun getMovies(): Call<ListMoviesResponseWrapper>

    @GET("search/movie/")
    fun searchMovies(@Query("query") name: String): Call<SearchMoviesResponseWrapper>
}
