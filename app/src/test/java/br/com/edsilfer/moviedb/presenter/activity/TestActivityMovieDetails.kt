package br.com.edsilfer.moviedb.controller.activity

import android.content.Intent
import android.widget.TextView
import br.com.edsilfer.moviedb.BuildConfig
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.ResourceProvider
import br.com.edsilfer.moviedb.presenter.activities.ActivityMovieDetails
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by User on 02/10/2016.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class TestActivityMovieDetails {
    @Test
    fun testActivityMovieDetails() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra("MOVIE", ResourceProvider.movie)
        val mActivity = Robolectric.buildActivity(ActivityMovieDetails::class.java).withIntent(intent).create().get()
        val title = mActivity.findViewById(R.id.movie_title) as TextView
        assertThat(title.text.toString(), `is`(ResourceProvider.movie.title))
    }
}
