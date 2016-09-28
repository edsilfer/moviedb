package br.com.edsilfer.moviedb.service

import br.com.edsilfer.moviedb.model.JSONContract
import com.google.gson.*
import org.json.JSONObject
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

object JSONParser {
    private val mGson: Gson = GsonBuilder().registerTypeAdapter(Date::class.java, object : JsonDeserializer<java.util.Date> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
            val df = SimpleDateFormat(JSONContract.DATE_FORMAT)
            try {
                return df.parse(json!!.asString)
            } catch (e: ParseException) {
                return null
            }
        }
    }).create()


    object Movie {
        fun list(root: String, source: JSONObject): List<br.com.edsilfer.moviedb.model.Movie>? {
            return mGson.fromJson(source.getJSONArray(root).toString(), Array<br.com.edsilfer.moviedb.model.Movie>::class.java).asList()
        }
    }
}
