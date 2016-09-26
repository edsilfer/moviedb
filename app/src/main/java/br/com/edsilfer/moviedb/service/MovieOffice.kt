package br.com.edsilfer.moviedb.service

import android.net.Uri
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.format
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import com.android.volley.Request
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

class MovieOffice(var mRequestService: RestTemplate) {

    fun listMovies() {
        try {
            val url = Uri.Builder().scheme(
                    App.getContext().getString(R.string.str_server_routes_base_protocol)).
                    authority(App.getContext().getString(R.string.str_server_routes_base_url)).
                    authority(App.getContext().getString(R.string.str_server_routes_base_api_version)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_service_discover)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_entity_movie)).
                    appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_primary_release_date_get), Date().format("yyyy-MM-dd")).
                    appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_api_key), App.getContext().getString(R.string.str_server_routes_base_api_v3))

            log("generated URL: ${url.toString()}")

            mRequestService.setUrl(url.toString())
            // mRequestService.setPayload(payload)
            mRequestService.setBody(EventCatalog.e0000, Request.Method.POST)
            mRequestService.execute(App.getContext())
        } catch (e: Exception) {
            log("Unable to list movies")
        }
    }
}
