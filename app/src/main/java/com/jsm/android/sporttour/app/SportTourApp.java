package com.jsm.android.sporttour.app;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by admin on 7/26/2016.
 */
public class SportTourApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.getAutoInstance(this);
    }
}
