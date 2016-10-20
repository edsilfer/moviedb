package br.com.edsilfer.moviedb.infrastructure.retrofit

import br.com.edsilfer.kotlin_support.service.log
import br.com.edsilfer.moviedb.R
import br.com.edsilfer.moviedb.infrastructure.App
import br.com.edsilfer.moviedb.model.JSONContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by User on 19/10/2016.
 */

class RetrofitManager() {
    internal var gson: Gson
    internal val client : OkHttpClient
    internal var retrofit: Retrofit

    init {
        client =  OkHttpClient.Builder().addInterceptor ({ chain ->
            var request = chain.request()
            val url = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                            App.getContext().resources.getString(R.string.str_server_routes_attr_api_key),
                            App.getContext().resources.getString(R.string.str_server_routes_base_api_v3)
                    ).build()

            log("Performing HTTP request to: $url")

            request = request.newBuilder().url(url).build();
            chain.proceed(request)
        }).build()

        gson = GsonBuilder()
                .setDateFormat(JSONContract.DATE_FORMAT)
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(App.getContext().resources.getString(R.string.str_comm_base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    fun getInstance(): Retrofit {
        return retrofit
    }
}
