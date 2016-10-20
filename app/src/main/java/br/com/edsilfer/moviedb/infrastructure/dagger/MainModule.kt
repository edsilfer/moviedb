package br.com.edsilfer.moviedb.infrastructure.dagger

import br.com.edsilfer.moviedb.infrastructure.retrofit.RetrofitManager
import br.com.edsilfer.moviedb.infrastructure.retrofit.RestAPIEndPoint
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides the instantiation for the managed classes of dagger 2
 */
@Module
class MainModule() {
    @Provides
    @Singleton
    fun provideRestAPI(): RestAPIEndPoint {
        return RetrofitManager().getInstance().create(RestAPIEndPoint::class.java)
    }
}
