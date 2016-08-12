package com.jsm.android.sporttour.app.tournaments;

import android.support.test.espresso.Espresso;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.*;
import static org.hamcrest.Matcher.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.tournament.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jonathan on 8/12/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TournamentsUITest {
    @Rule
    public ActivityTestRule<MainActivity> maRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void loadTournamentsTest(){
        onView(withText(R.string.progress_dialog_message)).check(matches(isDisplayed()));
    }
}
