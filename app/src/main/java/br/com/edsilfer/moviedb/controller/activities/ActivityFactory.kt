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

            if (activity is ActivityHomepage) {
                result.menuFile = R.menu.mnu_act_homepage
                result.contentView = R.layout.act_homepage
                result.title = App.getContext().getString(R.string.str_act_homepage_label)
            } else if (activity is ActivityMovie) {
                result.menuFile = R.menu.mnu_act_movie_dtls
                result.contentView = R.layout.act_movie_details
                result.title = App.getContext().getString(R.string.str_act_movie_dtls_label)
            }

            return result
        }
    }

}
