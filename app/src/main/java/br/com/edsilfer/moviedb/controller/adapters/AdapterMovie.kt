package br.com.edsilfer.moviedb.controller.adapters

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.Constants
import br.com.edsilfer.moviedb.controller.activities.ActivityMovieDetails
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class AdapterMovie(private val mActivity: AppCompatActivity, private val mDataSet: List<Movie>, private val mLayout: Int) : RecyclerView.Adapter<AdapterMovie.MovieHolder>() {

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovie.MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(mLayout, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = mDataSet[position]
        setCover(holder, movie)
        setTitle(holder, movie)
        onCoverClicked(holder, movie)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    // =============================================================================================
    private fun setTitle(holder: MovieHolder, movie: Movie) {
        holder.name.text = movie.original_title
    }

    private fun setCover(holder: MovieHolder, movie: Movie) {
        Picasso.with(mActivity)
                .load(movie.cover_url)
                .fit()
                .centerCrop()
                .into(holder.cover, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        holder.loadingWrapper.visibility = LinearLayout.GONE
                    }

                    override fun onError() {
                        log("Unable to load movie cover")
                    }
                })
    }

    private fun onCoverClicked(holder: MovieHolder, movie: Movie) {
        holder.cover.setOnClickListener {
            val intent = Intent(mActivity, ActivityMovieDetails::class.java)
            intent.putExtra(Constants.ActivityCommunication.ATTR_MOVIE, movie)
            mActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle())
        }
    }

    // VIEW HOLDER =================================================================================
    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cover: ImageView
        val name: TextView
        val loadingWrapper: LinearLayout

        init {
            cover = v.findViewById(R.id.cover) as ImageView
            name = v.findViewById(R.id.name) as TextView
            loadingWrapper = v.findViewById(R.id.loading_wrapper) as LinearLayout
        }
    }
}
