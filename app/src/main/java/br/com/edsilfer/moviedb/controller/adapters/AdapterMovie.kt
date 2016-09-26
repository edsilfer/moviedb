package br.com.edsilfer.moviedb.controller.adapters

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.Movie
import javax.inject.Inject

/**
 * Created by User on 26/09/2016.
 */

class AdapterMovie(private val mActivity: AppCompatActivity, private val mDataSet: List<Movie>) : RecyclerView.Adapter<AdapterMovie.ItemHolder>() {

    // LIFECYCLE ===================================================================================
    init {
        App.component?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovie.ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsc_util_movie_large_horizontal, parent, false)
        return ItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val Movie = mDataSet[position]
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    // VIEW HOLDER =================================================================================
    class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cover: ImageView
        val name: TextView

        init {
            cover = v.findViewById(R.id.cover) as ImageView
            name = v.findViewById(R.id.name) as TextView
        }
    }
}
