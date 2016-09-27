package br.com.edsilfer.moviedb.service

import br.com.edsilfer.moviedb.model.JSONContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

/**
 * Created by User on 26/09/2016.
 */

object JSONParser {
    private val mGson: Gson = GsonBuilder().setDateFormat(JSONContract.DATE_FORMAT).create()

    object Movie {
        fun list(root: String, source: JSONObject): List<br.com.edsilfer.moviedb.model.Movie>? {
            return mGson.fromJson(source.getJSONArray(root).toString(), Array<br.com.edsilfer.moviedb.model.Movie>::class.java).asList()
        }
    }
}
