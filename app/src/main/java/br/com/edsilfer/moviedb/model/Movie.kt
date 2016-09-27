package br.com.edsilfer.moviedb.model

import com.google.gson.Gson
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

data class Movie(
        var adult: Boolean,
        var overview: String,
        var release_date: Date,
        var genre_ids: List<Int>,
        var id: Long,
        var original_title: String,
        var original_language: String,
        var title: String,
        var backdrop_path: String,
        var popularity: Double,
        var vote_count: Int,
        var video: Boolean,
        var vote_average: Double,
        var poster_path: String,
        var still_path: String,
        var include_image_language: String
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
