package br.com.edsilfer.moviedb.presenter.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import br.com.edsilfer.kotlin_support.service.*
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import br.com.edsilfer.moviedb.model.SearchMoviesResponseWrapper
import br.com.edsilfer.moviedb.presenter.adapters.AdapterMovie
import br.com.edsilfer.moviedb.infrastructure.retrofit.RestAPIEndPoint
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_search_movie.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

/**
 * Provides the binding methods for Search Movie Activity layout elements
 */
class ActivitySearchMovie : ActivityTemplate() {

    companion object {
        private val QUERY_PLACEHOLDER = "XYzG$%Y78"
    }

    @Inject
    lateinit var mWebAPI: RestAPIEndPoint

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun startResources() {
        super.startResources()
        loadBackground()
        setQueryListener()
    }

    override fun setupActivity(): ActivitySetup {
        return ActivityFactory.getInstance(this)
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
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
        doAsync {
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
                        movies.initListItems(
                                this@ActivitySearchMovie as AppCompatActivity,
                                LinearLayoutManager.VERTICAL,
                                null,
                                adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
                    }
                }
            } else {
                movies.visibility = RecyclerView.GONE
                result_not_found_wrapper.visibility = CardView.VISIBLE
                result_not_found.text = result_not_found.text.toString().replace(QUERY_PLACEHOLDER, query_container.text.toString())
            }
        }
    }

    private fun setQueryListener() {
        query_container.addTextChangedListener(object : QueryWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleNavigationIcon(query_container.text.toString())
                if ("" != query_container.text.toString()) {
                    hideIndeterminateProgressBar()
                    showIndeterminateProgressBar()

                    mWebAPI.searchMovies(query_container.text.toString()).enqueue(object : Callback<SearchMoviesResponseWrapper> {
                        override fun onResponse(call: Call<SearchMoviesResponseWrapper>?, response: retrofit2.Response<SearchMoviesResponseWrapper>?) {
                            hideIndeterminateProgressBar()
                            loadResults(response!!.body()!!.results)
                        }

                        override fun onFailure(call: Call<SearchMoviesResponseWrapper>?, t: Throwable?) {
                            log("error trying to perform movie search ${t!!.message!!}")
                            hideIndeterminateProgressBar()
                            showErrorPopUp(R.string.str_error_list_upcoming_events)
                        }
                    })
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
