package br.com.edsilfer.moviedb.model

/**
 * Created by User on 26/09/2016.
 */

data class Movie(
        var original_title: String,
        var overview: String,
        var poster_path: String,
        var backdrop_path: String,
        var still_path: String,
        var include_image_language: String
)
