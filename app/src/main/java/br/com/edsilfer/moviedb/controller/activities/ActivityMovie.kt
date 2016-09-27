package br.com.edsilfer.moviedb.controller.activities

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.edsilfer.bidder.util.customSnackbar
import br.com.edsilfer.kiwi.layout.RecyclerViewUtil
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.controller.adapters.AdapterMovie
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.JSONContract
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.JSONParser
import br.com.edsilfer.moviedb.service.comm.Postman
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class ActivityMovie : br.com.edsilfer.moviedb.controller.activities.ActivityTemplate() {

    @Inject
    lateinit var mPostman: Postman

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getActivitySetup(this)
    }

    override fun startResources() {
        window.setBackgroundDrawable(getDrawable(R.drawable.img_background))
        mPostman.listMovies()
    }
}

