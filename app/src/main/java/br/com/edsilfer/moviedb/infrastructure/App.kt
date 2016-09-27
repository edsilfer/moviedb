package br.com.edsilfer.moviedb.infrastructure

import android.app.Application
import android.content.Context
import br.com.edsilfer.bidder.infrastructure.dagger.MainModule
import br.com.edsilfer.moviedb.infrastructure.dagger.DaggerMainComponent
import br.com.edsilfer.moviedb.infrastructure.dagger.MainComponent
import br.com.edsilfer.moviedb.service.comm.RestTemplate

/**
 * Created by User on 26/09/2016.
 */

class App : Application() {
    companion object {
        private var mApp: App? = null
        private var mComponent: MainComponent? = null

        val application: Application?
            get() = mApp

        val component: MainComponent?
            get() = mComponent

        fun getContext(): Context {
            if (mApp == null) {
                return Application()
            }
            return mApp!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        mApp = this
        mComponent = DaggerMainComponent.builder().mainModule(MainModule(RestTemplate())).build()
    }
}
