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
import kotlinx.android.synthetic.main.act_homepage.*
import kotlinx.android.synthetic.main.rsc_navigation_drawer_header.*
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

class DrawerController(var mActivity: AppCompatActivity) : NavigationView.OnNavigationItemSelectedListener {

    private val mDrawerLayout: DrawerLayout
    private val mNavigationView: NavigationView

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
            }
        }

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
        }

        val drawer = mActivity.findViewById(R.id.drawer_layout) as DrawerLayout?
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }
}
