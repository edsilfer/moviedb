package br.com.edsilfer.moviedb.infrastructure

import android.app.Application
import android.content.Context
import br.com.edsilfer.bidder.infrastructure.dagger.MainModule
import br.com.edsilfer.moviedb.infrastructure.dagger.DaggerMainComponent
import br.com.edsilfer.moviedb.infrastructure.dagger.MainComponent
import br.com.edsilfer.moviedb.service.comm.RestTemplate

/**
 * High level class that provides wide context object for accessing app resource as well as starting
 * app level resources
 */
class App : Application() {
    // STATIC ACCESSORS ============================================================================
    companion object {
        private var mApp: App? = null
        private var mComponent: MainComponent? = null

        val application: Application?
            get() = mApp

        val component: MainComponent?
            get() {
                if (null == mComponent) {
                    return DaggerMainComponent.builder().mainModule(MainModule(RestTemplate())).build()
                } else {
                    return mComponent
                }
            }

        fun getContext(): Context {
            if (mApp == null) {
                return Application()
            }
            return mApp!!.applicationContext
        }
    }

    // LIFECYCLE ===================================================================================
    override fun onCreate() {
        super.onCreate()
        mApp = this
        mComponent = DaggerMainComponent.builder().mainModule(MainModule(RestTemplate())).build()
    }
}
