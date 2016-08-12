package com.jsm.android.sporttour.app.di.module;

import com.jsm.android.sporttour.app.service.TournamentRepository;
import com.jsm.android.sporttour.app.service.TournamentRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jonathan on 8/12/16.
 */
@Module
public class TournamentRepositoryModule {

    @Provides @Singleton
    public TournamentRepository getTournamentRepository(){
        return TournamentRepositoryImpl.getInstance();
    }
}
