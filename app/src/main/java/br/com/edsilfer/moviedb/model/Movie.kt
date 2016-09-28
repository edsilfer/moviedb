package br.com.edsilfer.moviedb.model

import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import com.google.gson.Gson
import java.io.Serializable
import java.util.*

/**
 * Dictionary (data class) for Movie entity commonly traffic as payload of the API
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
) : Serializable {
    val cover_url: String
        get() = "${App.getContext().getString(R.string.str_server_routes_base_image_url)}//${poster_path}"

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
