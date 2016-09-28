package br.com.edsilfer.moviedb.controller.activities;

import android.os.Bundle;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsActivity;

import br.com.edsilfer.moviedb.R;


/**
 * Created by User on 11/09/2016.
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