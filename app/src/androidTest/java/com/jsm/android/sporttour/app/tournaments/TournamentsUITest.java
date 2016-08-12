package com.jsm.android.sporttour.app.tournaments;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jsm.android.sporttour.app.tournament.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by jonathan on 8/12/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TournamentsUITest {
    @Rule
    public ActivityTestRule<MainActivity> maRule = new ActivityTestRule<MainActivity>(MainActivity.class);
}
