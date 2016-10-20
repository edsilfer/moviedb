package br.com.edsilfer.moviedb.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchMoviesResponseWrapper(
        @SerializedName("page")
        val page: Int = 0,
        @SerializedName("results")
        val results: List<Movie> = mutableListOf<Movie>(),
        @SerializedName("total_results")
        val totalResults: Int = 0,
        @SerializedName("total_pages")
        val totalPages: Int = 0
) : Serializable {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
