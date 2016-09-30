package br.com.edsilfer.moviedb.service.comm

import android.net.Uri
import android.util.Log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.commons.log
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import com.android.volley.Request

/**
 * Responsible to manage all API calls related to the Movie entity
 */
open class MovieOffice(var mRequestService: RestTemplate) {

    // PUBLIC INTERFACE ============================================================================
    fun list() {
        try {
            execute(
                    EventCatalog.e0000,
                    Request.Method.GET,
                    getListMoviesURL()
            )
        } catch (e: Exception) {
            Log.e(MovieOffice::class.simpleName, "Unable to complete list movies request: ${e.message}")
        }
    }

    fun search(query: String) {
        try {
            execute(
                    EventCatalog.e0001,
                    Request.Method.GET,
                    getSearchMovieURL(query)
            )
        } catch (e: Exception) {
            Log.e(MovieOffice::class.simpleName, "Unable to complete search movies request: ${e.message}")
        }
    }

    // =============================================================================================
    private fun execute(event: EventCatalog, method: Int, url: Uri.Builder?) {
        mRequestService.setUrl(url.toString())
        mRequestService.setBody(event, method)
        mRequestService.execute(App.getContext())
    }

    private fun getSearchMovieURL(query: String): Uri.Builder? {
        val url = Uri.Builder().scheme(
                App.getContext().getString(R.string.str_server_routes_base_protocol)).
                authority(App.getContext().getString(R.string.str_server_routes_base_url)).
                appendPath(App.getContext().getString(R.string.str_server_routes_base_api_version)).
                appendPath(App.getContext().getString(R.string.str_server_routes_service_search)).
                appendPath(App.getContext().getString(R.string.str_server_routes_entity_movie)).
                appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_query), query).
                appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_api_key), App.getContext().getString(R.string.str_server_routes_base_api_v3))
        return url
    }

    private fun getListMoviesURL(): Uri.Builder? {
        val url = Uri.Builder().scheme(
                App.getContext().getString(R.string.str_server_routes_base_protocol)).
                authority(App.getContext().getString(R.string.str_server_routes_base_url)).
                appendPath(App.getContext().getString(R.string.str_server_routes_base_api_version)).
                appendPath(App.getContext().getString(R.string.str_server_routes_entity_movie)).
                appendPath(App.getContext().getString(R.string.str_server_routes_attr_upcoming)).
                appendQueryParameter(App.getContext().getString(R.string.str_server_routes_attr_api_key), App.getContext().getString(R.string.str_server_routes_base_api_v3))
        return url
    }
}
