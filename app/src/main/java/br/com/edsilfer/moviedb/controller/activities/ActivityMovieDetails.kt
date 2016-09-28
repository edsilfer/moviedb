package br.com.edsilfer.moviedb.controller.activities

import android.support.v7.widget.Toolbar
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.Constants
import br.com.edsilfer.moviedb.commons.format
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.service.comm.Postman
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_movie_details.*
import kotlinx.android.synthetic.main.rsc_movie_overview.*
import kotlinx.android.synthetic.main.rsc_movie_header.*
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class ActivityMovieDetails : ActivityTemplate() {

    @Inject
    lateinit var mPostman: Postman

    private var mMovie: Movie? = null

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getActivitySetup(this)
    }

    override fun startResources() {
        super.startResources()
        window.setBackgroundDrawable(getDrawable(R.drawable.img_background))
        mMovie = intent.extras.getSerializable(Constants.ActivityCommunication.ATTR_MOVIE) as Movie
        if (null == mMovie) throw IllegalArgumentException("${ActivityMovieDetails::class.simpleName} requires a Movie object to start")
        loadMovieDetails()
        loadOverview()
        mPostman.listMovies()
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onNavigationClicked() {
        finish()
    }

    // =============================================================================================
    private fun loadMovieDetails() {
        loadCover()
        movie_title.text = mMovie!!.title
        adult.text = mMovie!!.adult.toString()
        popularity.text = mMovie!!.popularity.toString()
        release_date.text = mMovie!!.release_date.format("yyyy-MM-dd")
        vote.text = "votes: ${mMovie!!.vote_count} | score: %.2f".format(mMovie!!.vote_average * 4 / 10)
        rating_bar.rating = (mMovie!!.vote_average * 4 / 10).toFloat()
    }

    private fun loadCover() {
        Picasso.with(this).load(mMovie!!.cover_url).fit().centerCrop().into(cover)
    }

    private fun loadOverview() {
        movie_overview.text = if (null != mMovie) mMovie!!.overview else getString(R.string.str_act_movie_dtls_txt_overview_not_found)
    }
}

