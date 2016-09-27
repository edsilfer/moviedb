package br.com.edsilfer.bidder.infrastructure.dagger

import br.com.edsilfer.kiwi.layout.RecyclerViewUtil
import br.com.edsilfer.moviedb.service.comm.MovieOffice
import br.com.edsilfer.moviedb.service.comm.Postman
import br.com.edsilfer.moviedb.service.comm.RestTemplate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by r720929 on 14/06/2016.
 */
@Module
class MainModule(private val mRequestService: RestTemplate) {

    // CONTROLLERS PROVIDERS =======================================================================
    @Provides
    @Singleton
    fun provideRecyclerViewUtil(): RecyclerViewUtil {
        return RecyclerViewUtil()
    }

    // COMM OFFICES ================================================================================
    @Provides
    @Singleton
    fun providePostman(): Postman {
        return Postman()
    }

    @Provides
    @Singleton
    fun provideMovieOffice(): MovieOffice {
        return MovieOffice(mRequestService)
    }
}
