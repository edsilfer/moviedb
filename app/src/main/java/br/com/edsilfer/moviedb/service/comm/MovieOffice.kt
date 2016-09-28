package br.com.edsilfer.moviedb.service.comm

import android.net.Uri
import br.com.edsilfer.bidder.util.log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.format
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.service.comm.RestTemplate
import com.android.volley.Request
import java.util.*

/**
 * Created by User on 26/09/2016.
 */

class MovieOffice(var mRequestService: RestTemplate) {

    fun list() {
        try {
            val url = Uri.Builder().scheme(
                    App.getContext().getString(R.string.str_server_routes_base_protocol)).
                    authority(App.getContext().getString(R.string.str_server_routes_base_url)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_base_api_version)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_entity_movie)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_attr_upcoming)).
                    appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_api_key), App.getContext().getString(R.string.str_server_routes_base_api_v3))

            mRequestService.setUrl(url.toString())
            mRequestService.setBody(EventCatalog.e0000, Request.Method.GET)
            mRequestService.execute(App.getContext())
        } catch (e: Exception) {
            log("Unable to complete list movies request: ${e.message}")
        }
    }

    fun search(query: String) {
        try {
            val url = Uri.Builder().scheme(
                    App.getContext().getString(R.string.str_server_routes_base_protocol)).
                    authority(App.getContext().getString(R.string.str_server_routes_base_url)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_base_api_version)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_service_search)).
                    appendPath(App.getContext().getString(R.string.str_server_routes_entity_movie)).
                    appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_query), query).
                    appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_api_key), App.getContext().getString(R.string.str_server_routes_base_api_v3))

            mRequestService.setUrl(url.toString())
            mRequestService.setBody(EventCatalog.e0001, Request.Method.GET)
            mRequestService.execute(App.getContext())
        } catch (e: Exception) {
            log("Unable to complete search movies request: ${e.message}")
        }
    }
}
