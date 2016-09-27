package br.com.edsilfer.moviedb.controller.adapters

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class AdapterMovie(private val mActivity: AppCompatActivity, private val mDataSet: List<Movie>) : RecyclerView.Adapter<AdapterMovie.MovieHolder>() {

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovie.MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsc_util_movie_large_horizontal, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = mDataSet[position]
        setCover(holder, movie)
        setTitle(holder, movie)
    }

    // =============================================================================================
    private fun setTitle(holder: MovieHolder, movie: Movie) {
        holder.name.text = movie.original_title
    }

    private fun setCover(holder: MovieHolder, movie: Movie) {
        Picasso.with(mActivity)
                .load("${mActivity.getString(R.string.str_server_routes_base_image_url)}//${movie.poster_path}")
                .fit()
                .centerCrop()
                .into(holder.cover)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    // VIEW HOLDER =================================================================================
    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cover: ImageView
        val name: TextView

        init {
            cover = v.findViewById(R.id.cover) as ImageView
            name = v.findViewById(R.id.name) as TextView
        }
    }
}