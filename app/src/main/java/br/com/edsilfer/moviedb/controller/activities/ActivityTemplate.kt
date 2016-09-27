package br.com.edsilfer.moviedb.controller.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ImageView
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.comm.NotificationCenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_homepage.*

/**
 * Created by edgar on 02-May-16.
 */
abstract class ActivityTemplate : AppCompatActivity() {

    private var isRunning: Boolean? = true
    private var mSetup: ActivitySetup? = null

    abstract fun setupActivity(): ActivitySetup

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.startResources()
    }

    open fun startResources() {
        mSetup = setupActivity()
        if (mSetup != null) {
            try {
                setContentView(mSetup!!.contentView!!)
                toolbar.title = mSetup!!.title
                registerForEvents()
            } catch (e: Exception) {
                startActivity(Intent(this, ActivityHomepage::class.java))
            }
        }
    }

    fun loadBackgroundImage(wrapper: ImageView?) {
        if (null != wrapper) {
            Picasso.with(this).load(R.drawable.img_background).fit().centerCrop().into(wrapper)
        }
    }

    public override fun onDestroy() {
        unregisterForEvents()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        //mSetup!!.menuFile ?: menuInflater.inflate(mSetup!!.menuFile!!, menu)
        return true
    }

    fun registerForEvents() {
        val executors = setEventHandlers()
        if (executors!!.size > 0) {
            for (event in executors.keys) {
                NotificationCenter.RegistrationCenter.registerForEvent(event, executors[event]!!)
            }
        }
    }

    fun unregisterForEvents() {
        val executors = this.setEventHandlers()
        if (null != executors && executors.size > 0) {
            for (key in executors.keys) {
                NotificationCenter.RegistrationCenter.unregisterForEvent(key, executors[key]!!)
            }
        }
    }

    fun isRunning(): Boolean {
        return this.isRunning!!
    }

    open fun setEventHandlers(): Map<EventCatalog, TaskExecutor>? {
        return null
    }
}
