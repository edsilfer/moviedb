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
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.ListMoviesResponseWrapper
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.presenter.DrawerController
import br.com.edsilfer.moviedb.presenter.adapters.AdapterMovie
import br.com.edsilfer.moviedb.service.comm.TheMovieDBEndPoints
import kotlinx.android.synthetic.main.act_homepage.*
import kotlinx.android.synthetic.main.rsc_homepage_content.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

/**
 * Provides the binding methods for About Activity layout elements
 */
class ActivityHomepage : ActivityTemplate() {

    @Inject
    lateinit var mWebAPI: TheMovieDBEndPoints

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

        mWebAPI.getMovies().enqueue(object : Callback<ListMoviesResponseWrapper> {
            override fun onResponse(call: Call<ListMoviesResponseWrapper>?, response: retrofit2.Response<ListMoviesResponseWrapper>?) {
                hideCircularProgressBar()
                loadMovies(response!!.body()!!.results)
            }

            override fun onFailure(call: Call<ListMoviesResponseWrapper>?, t: Throwable?) {
                hideCircularProgressBar()
                showErrorPopUp(R.string.str_error_list_upcoming_events)
            }
        })
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

