package br.com.edsilfer.moviedb.service.comm

import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import org.json.JSONObject

import br.com.edsilfer.moviedb.model.enums.ResponseType
import br.com.edsilfer.moviedb.model.ResponseWrapper
import br.com.edsilfer.moviedb.model.enums.EventCatalog

/**
 * Handles common methods related to the assembly of a REST call
 */
class RestTemplate {

    private var mUrl: String? = null
    private var mPayload: JSONObject? = null
    private var mRequest: JsonObjectRequest? = null
    private var mRequestQueue: RequestQueue? = null

    // LIFECYCLE ===================================================================================
    init {
        this.mUrl = ""
    }

    // PUBLIC INTERFACE ============================================================================
    fun execute(context: Context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context)
        }
        mRequestQueue!!.add(this.mRequest!!)
    }

    fun setBody(event: EventCatalog, httpMethod: Int) {
        this.mRequest = JsonObjectRequest(
                httpMethod,
                mUrl,
                mPayload,
                Response.Listener<org.json.JSONObject> { response ->
                    val responseWrapper = ResponseWrapper(response, ResponseType.SUCCESS)
                    NotificationCenter.notify(event, responseWrapper)
                },
                Response.ErrorListener { error ->
                    val response = ResponseWrapper(error.message, ResponseType.ERROR)
                    NotificationCenter.notify(event, response)
                })
    }

    fun setPayload(mPayload: JSONObject) {
        this.mPayload = mPayload
    }

    fun setUrl(url: String) {
        this.mUrl = url
    }

    fun cancelRequests() {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(RequestQueue.RequestFilter { true })
        }
    }
}
