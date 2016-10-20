package br.com.edsilfer.moviedb.infrastructure.dagger;

import javax.inject.Singleton;

import br.com.edsilfer.moviedb.presenter.activities.ActivityHomepage;
import br.com.edsilfer.moviedb.presenter.activities.ActivityMovieDetails;
import br.com.edsilfer.moviedb.presenter.activities.ActivitySearchMovie;
import br.com.edsilfer.moviedb.presenter.adapters.AdapterMovie;
import dagger.Component;

/**
 * Inject the instance of the annotated parties
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
}




