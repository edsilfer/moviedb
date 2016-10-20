package br.com.edsilfer.moviedb.infrastructure.dagger

import br.com.edsilfer.moviedb.service.comm.RetrofitManager
import br.com.edsilfer.moviedb.service.comm.TheMovieDBEndPoints
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides the instantiation for the managed classes of dagger 2
 */
@Module
class MainModule() {

    // COMM OFFICES ================================================================================
    @Provides
    @Singleton
    fun provideWebAPI(): TheMovieDBEndPoints {
        return RetrofitManager().getInstance().create(TheMovieDBEndPoints::class.java)
    }
}
