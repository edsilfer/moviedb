package br.com.edsilfer.moviedb.presenter.activities

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.edsilfer.kotlin_support.service.hideCircularProgressBar
import br.com.edsilfer.kotlin_support.service.initListItems
import br.com.edsilfer.kotlin_support.service.showCircularProgressBar
import br.com.edsilfer.kotlin_support.service.showErrorPopUp
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.presenter.DrawerController
import br.com.edsilfer.moviedb.presenter.adapters.AdapterMovie
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.JSONContract
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.JSONParser
import br.com.edsilfer.moviedb.service.comm.Postman
import kotlinx.android.synthetic.main.act_homepage.*
import kotlinx.android.synthetic.main.rsc_homepage_content.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import javax.inject.Inject

/**
 * Provides the binding methods for About Activity layout elements
 */
class ActivityHomepage : ActivityTemplate() {

    @Inject
    lateinit var mPostman: Postman

    // NETWORK EVENTS ==============================================================================
    val event0000Handler = object : TaskExecutor {
        override fun executeOnSuccessTask(payload: Any) {
            hideCircularProgressBar()
            loadMovies(JSONParser.Movie.list(JSONContract.ATTR_RESULTS, payload as JSONObject)!!)
        }

        override fun executeOnErrorTask(payload: Any) {
            hideCircularProgressBar()
            showErrorPopUp(R.string.str_error_list_upcoming_events)
        }
    }

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getInstance(this)
    }

    override fun startResources() {
        super.startResources()
        setLeftSlideAnimationForCaller()
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> startActivity(Intent(this, ActivitySearchMovie::class.java))
        }
        return true
    }

    // =============================================================================================
    private fun loadMovies(results: List<Movie>) {
        doAsync {
            val adapter = AdapterMovie(
                    this@ActivityHomepage,
                    results,
                    R.layout.rsc_util_movie_large
            )

            runOnUiThread {
                movies.initListItems(
                        this@ActivityHomepage,
                        LinearLayoutManager.VERTICAL,
                        null,
                        adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
                )
            }
        }
    }
}

