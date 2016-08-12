package com.jsm.android.sporttour.app.di.component;

import com.jsm.android.sporttour.app.di.module.TournamentRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jonathan on 8/12/16.
 */
@Singleton
@Component(modules = {TournamentRepositoryModule.class})
public class SportAppComponent {
}
