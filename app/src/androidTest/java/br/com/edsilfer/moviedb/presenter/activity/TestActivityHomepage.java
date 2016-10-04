package br.com.edsilfer.moviedb.presenter.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.edsilfer.moviedb.R;
import br.com.edsilfer.moviedb.presenter.activities.ActivityHomepage;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by User on 28/09/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestActivityHomepage {

    @Rule
    public ActivityTestRule<ActivityHomepage> mActivityRule = new ActivityTestRule<>(
            ActivityHomepage.class);


    @Test
    public void checkLoadMovies() throws InterruptedException {
        Thread.sleep(2000);
        final RecyclerView movies = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.movies);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                movies.smoothScrollToPosition(7);
            }
        });

        Thread.sleep(1500);

        System.out.println("item count: " + movies.getAdapter().getItemCount());
        assert (movies.getAdapter().getItemCount() > 0);
    }
}
