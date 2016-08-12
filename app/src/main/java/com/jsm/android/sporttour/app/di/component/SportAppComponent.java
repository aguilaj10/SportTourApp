package com.jsm.android.sporttour.app.di.component;

import com.jsm.android.sporttour.app.di.module.ApplicationModule;
import com.jsm.android.sporttour.app.di.module.TournamentRepositoryModule;
import com.jsm.android.sporttour.app.service.TournamentRepository;
import com.jsm.android.sporttour.app.tournament.TournamentPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jonathan on 8/12/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, TournamentRepositoryModule.class})
public interface SportAppComponent {
    TournamentRepository createTournamentRepository();
    void inject(TournamentPresenter tournamentPresenter);
}
