package br.com.edsilfer.moviedb.controller.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.edsilfer.moviedb.R;
import br.com.edsilfer.moviedb.controller.activities.ActivityHomepage;
import br.com.edsilfer.moviedb.controller.activities.ActivitySearchMovie;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by User on 28/09/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestActivitySearchMovie {

    @Rule
    public ActivityTestRule<ActivitySearchMovie> mActivityRule = new ActivityTestRule<>(
            ActivitySearchMovie.class);

    @Test
    public void startSearchActivity() throws InterruptedException {
        final TextView query = (TextView) mActivityRule.getActivity().findViewById(R.id.query_container);
        final RecyclerView results = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.movies);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                query.setText("Engineer");
            }
        });
        Thread.sleep(1500);
        assert (results.getAdapter().getItemCount() > 0);
    }
}
