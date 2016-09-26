package br.com.edsilfer.moviedb.controller.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.kiwi.layout.RecyclerViewUtil
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.controller.adapters.AdapterMovie
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.NotificationCenter
import br.com.edsilfer.moviedb.service.Postman
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class ActivityHomepage : AppCompatActivity() {

    @Inject
    lateinit var mPostman: Postman
    @Inject
    lateinit var mRecyclerViewService: RecyclerViewUtil

    // NETWORK EVENTS ==============================================================================
    val event0000Handler = object : TaskExecutor {
        override fun executeOnSuccessTask(payload: Any) {
            log("List Movies Result Success")
        }

        override fun executeOnErrorTask(payload: Any) {
            log("List Movies Result Error")
        }
    }

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_homepage)
        setEventHandlers()
        mPostman.listMovies()
    }

    private fun setEventHandlers() {
        NotificationCenter.RegistrationCenter.registerForEvent(EventCatalog.e0000, event0000Handler)
    }

    // =============================================================================================
    private fun loadMovies() {
        val adapter = AdapterMovie(
                this,
                listOf<Movie>()
        )

        mRecyclerViewService.initListItems(
                this,
                R.id.movies,
                LinearLayoutManager.HORIZONTAL,
                null,
                adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }
}

