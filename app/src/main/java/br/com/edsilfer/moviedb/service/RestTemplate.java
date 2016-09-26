package br.com.edsilfer.moviedb.service;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import br.com.edsilfer.moviedb.model.ResponseType;
import br.com.edsilfer.moviedb.model.ResponseWrapper;
import br.com.edsilfer.moviedb.model.enums.EventCatalog;

/**
 * Created by edgar on 17/02/2016.
 */
public class RestTemplate {

    public static final String TAG = "RestTemplate";

    private String mUrl;
    private JSONObject mPayload;
    private JsonObjectRequest mRequest;
    private RequestQueue mRequestQueue;

    public RestTemplate() {
        this.mUrl = "";
    }

    public void execute(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        mRequestQueue.add(this.mRequest);
    }


    public void setBody(final EventCatalog event, int httpMethod) {
        this.mRequest = new JsonObjectRequest(
                httpMethod,
                this.mUrl,
                this.mPayload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ResponseWrapper responseWrapper = new ResponseWrapper(response, ResponseType.SUCCESS);
                        NotificationCenter.INSTANCE.notify(event, responseWrapper);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ResponseWrapper response = new ResponseWrapper(error.getMessage(), ResponseType.ERROR);
                        NotificationCenter.INSTANCE.notify(event, response);
                    }
                });
    }

    public void setPayload(JSONObject mPayload) {
        this.mPayload = mPayload;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void cancelRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

}
