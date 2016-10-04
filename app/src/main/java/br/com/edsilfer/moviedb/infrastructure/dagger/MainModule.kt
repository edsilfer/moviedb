package br.com.edsilfer.bidder.infrastructure.dagger

import br.com.edsilfer.moviedb.service.comm.MovieOffice
import br.com.edsilfer.moviedb.service.comm.Postman
import br.com.edsilfer.moviedb.service.comm.RestTemplate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides the instantiation for the managed classes of dagger 2
 */
@Module
class MainModule(private val mRequestService: RestTemplate) {

    // COMM OFFICES ================================================================================
    @Provides
    @Singleton
    fun providePostman(): Postman {
        return Postman(mRequestService)
    }

    @Provides
    @Singleton
    fun provideMovieOffice(): MovieOffice {
        return MovieOffice(mRequestService)
    }
}
