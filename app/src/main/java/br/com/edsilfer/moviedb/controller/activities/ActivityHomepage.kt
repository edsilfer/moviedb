package br.com.edsilfer.moviedb.controller.activities

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import br.com.edsilfer.bidder.util.customSnackbar
import br.com.edsilfer.bidder.util.hideCircularProgressBar
import br.com.edsilfer.bidder.util.showCircularProgressBar
import br.com.edsilfer.kiwi.layout.RecyclerViewUtil
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.Constants
import br.com.edsilfer.moviedb.controller.DrawerController
import br.com.edsilfer.moviedb.controller.adapters.AdapterMovie
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.JSONContract
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.JSONParser
import br.com.edsilfer.moviedb.service.comm.Postman
import kotlinx.android.synthetic.main.act_homepage.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class ActivityHomepage : ActivityTemplate() {

    @Inject
    lateinit var mPostman: Postman
    @Inject
    lateinit var mRecyclerViewService: RecyclerViewUtil


    // NETWORK EVENTS ==============================================================================
    val event0000Handler = object : TaskExecutor {
        override fun executeOnSuccessTask(payload: Any) {
            hideCircularProgressBar()
            loadMovies(JSONParser.Movie.list(JSONContract.ATTR_RESULTS, payload as JSONObject)!!)
        }

        override fun executeOnErrorTask(payload: Any) {
            hideCircularProgressBar()
            customSnackbar("List movies result error")
        }
    }

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getActivitySetup(this)
    }

    override fun startResources() {
        super.startResources()
        loadBackgroundImage(background)
        showCircularProgressBar()
        DrawerController(this).init()
        mPostman.listMovies()
    }

    override fun setEventHandlers(): Map<EventCatalog, TaskExecutor>? {
        return mapOf(
                Pair(EventCatalog.e0000, event0000Handler)
        )
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    // =============================================================================================
    private fun loadMovies(movies: List<Movie>) {
        doAsync {
            val adapter = AdapterMovie(
                    this@ActivityHomepage,
                    movies
            )
            runOnUiThread {
                mRecyclerViewService.initListItems(
                        this@ActivityHomepage,
                        R.id.movies,
                        LinearLayoutManager.VERTICAL,
                        null,
                        adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
            }
        }
    }
}

