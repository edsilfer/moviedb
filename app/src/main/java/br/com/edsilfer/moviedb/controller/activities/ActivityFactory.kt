package br.com.edsilfer.moviedb.controller.activities

import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App

/**
 * Created by User on 27/09/2016.
 */

class ActivityFactory {
    companion object {
        fun getActivitySetup(activity: Any): ActivitySetup {
            val result = ActivitySetup()

            when (activity) {
                is ActivityHomepage -> homePageSetup(result)
                is ActivityMovieDetails -> movieDetailsSetup(result)
                is ActivitySearchMovie -> searchMovieSetup(result)
            }
            return result
        }

        private fun searchMovieSetup(result: ActivitySetup) {
            result.menuFile = null
            result.contentView = R.layout.act_search_movie
            result.title = ""
            result.toolbarIcon = R.drawable.ic_arrow_left_white_24dp
        }

        private fun movieDetailsSetup(result: ActivitySetup) {
            result.menuFile = R.menu.mnu_act_movie_dtls
            result.contentView = R.layout.act_movie_details
            result.title = ""
            result.toolbarIcon = R.drawable.ic_arrow_left_white_24dp
        }

        private fun homePageSetup(result: ActivitySetup) {
            result.menuFile = R.menu.mnu_act_homepage
            result.contentView = R.layout.act_homepage
            result.title = App.getContext().getString(R.string.str_act_homepage_label)
            result.toolbarIcon = R.drawable.ic_menu_white_24dp
        }
    }
}
