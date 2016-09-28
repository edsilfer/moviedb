package br.com.edsilfer.moviedb.infrastructure.dagger;

import javax.inject.Singleton;

import br.com.edsilfer.bidder.infrastructure.dagger.MainModule;
import br.com.edsilfer.moviedb.controller.activities.ActivityHomepage;
import br.com.edsilfer.moviedb.controller.activities.ActivityMovieDetails;
import br.com.edsilfer.moviedb.controller.activities.ActivitySearchMovie;
import br.com.edsilfer.moviedb.controller.adapters.AdapterMovie;
import br.com.edsilfer.moviedb.service.comm.Postman;
import dagger.Component;

/**
 * Created by edgar on 09-May-16.
 */
@Singleton
@Component(
        modules = {
                MainModule.class,
        })
public interface MainComponent {

    // ACTIVITIES ==================================================================================
    void inject(ActivityHomepage activityHomepage);

    void inject(ActivityMovieDetails activityMovie);

    void inject(ActivitySearchMovie activitySearchMovie);

    // ADAPTERS ====================================================================================
    void inject(AdapterMovie adapterMovie);

    // COMM ========================================================================================
    void inject(Postman postman);
}




