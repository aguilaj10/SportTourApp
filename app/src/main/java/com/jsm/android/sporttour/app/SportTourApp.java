package com.jsm.android.sporttour.app;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.branch.referral.Branch;

/**
 * Created by admin on 7/26/2016.
 */
public class SportTourApp extends Application {
    private RefWatcher refWatcher;
    private static SportTourApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        instance = this;
    }

    public static SportTourApp getInstance(){
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        return getInstance().refWatcher;
    }

    public static void destroy(Object object) {
        if (getInstance().refWatcher != null)
            getInstance().refWatcher.watch(object);
    }
}
