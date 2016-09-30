package br.com.edsilfer.moviedb.service

import org.json.JSONException
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import br.com.edsilfer.moviedb.model.Movie

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat

/**
 * Created by User on 29/09/2016.
 */
@RunWith(MockitoJUnitRunner::class)
class TestJSONParser {

    companion object {
        private val mRoot = "results"
        private var mSampleJSON: JSONObject? = null
    }

    @Before
    @Throws(JSONException::class)
    fun setUp() {
        mSampleJSON = JSONObject("{" +
                "\"page\": 1," +
                "\"results\": [{" +
                "\"poster_path\": \"\\/oFOG2yIRcluKfTtYbzz71Vj9bgz.jpg\"," +
                "\"adult\": false," +
                "\"overview\": \"After waking up in a hospital with amnesia, professor Robert Langdon and a doctor must race against time to foil a deadly global plot.\"," +
                "\"release_date\": \"2016-10-13\"," +
                "\"genre_ids\": [9648, 53]," +
                "\"id\": 207932," +
                "\"original_title\": \"Inferno\"," +
                "\"original_language\": \"en\"," +
                "\"title\": \"Inferno\"," +
                "\"backdrop_path\": \"\\/mL1hZ8Z7L1Px8tIzalnYKKdhUqo.jpg\"," +
                "\"popularity\": 4.294437," +
                "\"vote_count\": 17," +
                "\"video\": false," +
                "\"vote_average\": 4.91" +
                "\t}, {" +
                "\"poster_path\": \"\\/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg\"," +
                "\"adult\": false," +
                "\"overview\": \"Jack Reacher returns to the headquarters of his old unit, only to find out he's now accused of a 16-year-old homicide.\"," +
                "\"release_date\": \"2016-10-19\"," +
                "\"genre_ids\": [53, 28, 80, 18, 9648]," +
                "\"id\": 343611," +
                "\"original_title\": \"Jack Reacher: Never Go Back\"," +
                "\"original_language\": \"en\"," +
                "\"title\": \"Jack Reacher: Never Go Back\"," +
                "\"backdrop_path\": \"\\/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg\"," +
                "\"popularity\": 3.011832," +
                "\"vote_count\": 14," +
                "\"video\": false," +
                "\"vote_average\": 3.93" +
                "\t}]," +
                "\t\"dates\": {" +
                "\"maximum\": \"2016-10-22\"," +
                "\"minimum\": \"2016-10-01\"" +
                "\t}," +
                "\t\"total_pages\": 14," +
                "\t\"total_results\": 262" +
                "}")
    }

    @Test
    fun listMovies() {
        val movies = JSONParser.Movie.list(mRoot, mSampleJSON!!)
        assertNotNull(movies)
        assertThat(movies!!.size, `is`(2))
    }
}
