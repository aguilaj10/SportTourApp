package com.jsm.android.sporttour.app.di.module;

import android.content.Context;
import com.jsm.android.sporttour.app.SportTourApp;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jonathan on 8/12/16.
 */
@Module
public class ApplicationModule {
    private final SportTourApp application;

    public ApplicationModule(SportTourApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
