package br.com.edsilfer.moviedb.service

import br.com.edsilfer.moviedb.model.JSONContract
import com.google.gson.*
import org.json.JSONObject
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Parses the JSONObjects returned as payload from the API calls
 */
object JSONParser {
    private val mGson: Gson = GsonBuilder().registerTypeAdapter(Date::class.java, JsonDeserializer<java.util.Date> { json, typeOfT, context ->
        val df = SimpleDateFormat(JSONContract.DATE_FORMAT)
        try {
            return@JsonDeserializer df.parse(json!!.asString)
        } catch (e: ParseException) {
            return@JsonDeserializer null
        }
    }).create()

    // PUBLIC INTERFACE ============================================================================
    object Movie {
        fun list(root: String, source: JSONObject): List<br.com.edsilfer.moviedb.model.Movie>? {
            return mGson.fromJson(source.getJSONArray(root).toString(), Array<br.com.edsilfer.moviedb.model.Movie>::class.java).asList()
        }
    }
}
