package br.com.edsilfer.moviedb.model

import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
data class Movie(
        @SerializedName("poster_path")
        @Expose
        var posterPath: String? = null,
        @SerializedName("adult")
        @Expose
        var adult: Boolean? = null,
        @SerializedName("overview")
        @Expose
        var overview: String? = null,
        @SerializedName("release_date")
        @Expose
        var releaseDate: Date? = null,
        @SerializedName("genre_ids")
        @Expose
        var genreIds: List<Long> = ArrayList(),
        @SerializedName("id")
        @Expose
        var id: Long? = null,
        @SerializedName("original_title")
        @Expose
        var originalTitle: String? = null,
        @SerializedName("original_language")
        @Expose
        var originalLanguage: String? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("backdrop_path")
        @Expose
        var backdropPath: String? = null,
        @SerializedName("popularity")
        @Expose
        var popularity: Double? = null,
        @SerializedName("vote_count")
        @Expose
        var voteCount: Long? = null,
        @SerializedName("video")
        @Expose
        var video: Boolean? = null,
        @SerializedName("vote_average")
        @Expose
        var voteAverage: Double = 0.toDouble()) : Serializable {
    val coverUrl: String
        get() = "${App.getContext().getString(R.string.str_server_routes_base_image_url)}//${posterPath}"

    override fun toString(): String {
        return Gson().toJson(this)
    }
}