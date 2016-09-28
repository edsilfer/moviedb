package br.com.edsilfer.moviedb.controller.activities

import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.transition.Slide
import android.view.Gravity
import br.com.edsilfer.bidder.util.hideIndeterminateProgressBar
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.bidder.util.showErrorPopUp
import br.com.edsilfer.bidder.util.showIndeterminateProgressBar
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_search_movie.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by User on 28/09/2016.
 */

class ActivitySearchMovie : ActivityTemplate() {

    companion object {
        private val QUERY_PLACEHOLDER = "XYzG$%Y78"
    }

    @Inject
    lateinit var mPostman: Postman
    @Inject
    lateinit var mRecyclerViewService: RecyclerViewUtil

    // NETWORK EVENTS ==============================================================================
    val event0001Handler = object : TaskExecutor {
        override fun executeOnSuccessTask(payload: Any) {
            hideIndeterminateProgressBar()
            doAsync {
                loadResults(JSONParser.Movie.list(JSONContract.ATTR_RESULTS, payload as JSONObject)!!)
            }
        }

        override fun executeOnErrorTask(payload: Any) {
            log("Search movie failed")
            hideIndeterminateProgressBar()
            showErrorPopUp(R.string.str_error_search_movie_failed)
        }
    }

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun startResources() {
        super.startResources()
        window.exitTransition = Slide(Gravity.RIGHT)
        loadBackground()
        setQueryListener()
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getActivitySetup(this)
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setEventHandlers(): Map<EventCatalog, TaskExecutor>? {
        return mapOf(
                Pair(EventCatalog.e0001, event0001Handler)
        )
    }

    override fun onNavigationClicked() {
        if ("" == query_container.text.toString()) {
            finish()
        } else {
            query_container.text.clear()
        }
    }

    // =============================================================================================
    private fun loadBackground() {
        Picasso.with(this).load(R.drawable.img_background).fit().centerCrop().into(background)
    }

    private fun loadResults(results: List<Movie>) {
        if (results.size > 0) {
            movies.visibility = RecyclerView.VISIBLE
            result_not_found_wrapper.visibility = CardView.GONE
            doAsync {
                val adapter = AdapterMovie(
                        this@ActivitySearchMovie,
                        results,
                        R.layout.rsc_util_movie_small
                )
                runOnUiThread {
                    result_not_found_wrapper.visibility = CardView.GONE
                    mRecyclerViewService.initListItems(
                            this@ActivitySearchMovie,
                            R.id.movies,
                            LinearLayoutManager.VERTICAL,
                            null,
                            adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
                }
            }
        } else {
            log("results < 0")
            movies.visibility = RecyclerView.GONE
            result_not_found_wrapper.visibility = CardView.VISIBLE
            result_not_found.text = result_not_found.text.toString().replace(QUERY_PLACEHOLDER, query_container.text.toString())
        }
    }

    // =============================================================================================
    private fun setQueryListener() {
        query_container.addTextChangedListener(object : QueryWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleNavigationIcon(query_container.text.toString())
                if ("" != query_container.text.toString()) {
                    hideIndeterminateProgressBar()
                    mPostman.cancelRequests()
                    showIndeterminateProgressBar()
                    mPostman.searchMovie(query_container.text.toString())
                } else {
                    loadResults(listOf<Movie>())
                    result_not_found_wrapper.visibility = CardView.GONE
                }
            }
        })
    }

    private fun toggleNavigationIcon(query: String) {
        if ("" == query) toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp) else toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
    }

    /**
     * Interface created to make code cleaner
     */
    abstract class QueryWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }
}
