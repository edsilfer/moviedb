package br.com.edsilfer.moviedb.presenter.activities;

import android.os.Bundle;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsActivity;

import br.com.edsilfer.moviedb.R;


/**
 * Provides the binding methods for About Activity layout elements
 */
public class ActivityAbout extends LibsActivity {

    // LIFECYCLE ===================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setIntent(new LibsBuilder()
                .withActivityTitle(getResources().getString(R.string.app_name))
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withFields(R.string.class.getFields())
                .intent(this));

        super.onCreate(savedInstanceState);
    }
}