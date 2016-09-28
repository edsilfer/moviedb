package br.com.edsilfer.moviedb.controller

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.format
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_homepage.*
import kotlinx.android.synthetic.main.rsc_navigation_drawer_header.*
import java.util.*

/**
 * Handles DrawerController related method, including the menu action
 */
class DrawerController(var mActivity: AppCompatActivity) : NavigationView.OnNavigationItemSelectedListener {

    private val mDrawerLayout: DrawerLayout
    private val mNavigationView: NavigationView

    // LIFECYCLE ===================================================================================
    init {
        mDrawerLayout = mActivity.findViewById(R.id.drawer_layout) as DrawerLayout
        mNavigationView = mActivity.findViewById(R.id.nav_view) as NavigationView
    }

    fun init() {
        mNavigationView.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(
                mActivity,
                mDrawerLayout,
                mActivity.toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            override fun onDrawerClosed(view: View?) {
                super.onDrawerClosed(view)
                mActivity.invalidateOptionsMenu()
                syncState()
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                mActivity.invalidateOptionsMenu()
                syncState()

                mActivity.username.text = "Test User"
                mActivity.dob.text = Date().format("yyyy-MM-dd")
                mActivity.address.text = "Bentley St. N.: 458"
                Picasso.with(mActivity).load(R.drawable.ic_male_avatar).fit().centerCrop().into(mActivity.picture)
            }
        }

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_about -> openAbout()
        }

        val drawer = mActivity.findViewById(R.id.drawer_layout) as DrawerLayout?
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    // =============================================================================================
    private fun openAbout() {
        LibsBuilder()
                .withActivityTitle(mActivity.getString(R.string.app_name))
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .start(mActivity)
    }
}
