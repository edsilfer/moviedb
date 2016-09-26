package br.com.edsilfer.moviedb.infrastructure.dagger;

import javax.inject.Singleton;

import br.com.edsilfer.bidder.infrastructure.dagger.MainModule;
import br.com.edsilfer.moviedb.controller.activities.ActivityHomepage;
import br.com.edsilfer.moviedb.controller.adapters.AdapterMovie;
import br.com.edsilfer.moviedb.service.Postman;
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

    // ADAPTERS ====================================================================================
    void inject(AdapterMovie adapterMovie);

    // COMM ========================================================================================
    void inject(Postman postman);
}




