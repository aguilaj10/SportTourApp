package com.jsm.android.sporttour.app.service;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by jonathan on 8/12/16.
 */
public class TournamentRepoFactory {
    private final Context context;

    @Inject
    public TournamentRepoFactory(@NonNull Context context){
        this.context = context;
    }

    public TournamentRepository create(){
        //Verify connection
        //Verify cache
        //Then return the proper implementation
        return TournamentRepositoryImpl.getInstance();
    }
}
