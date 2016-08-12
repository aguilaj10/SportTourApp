package com.jsm.android.sporttour.app.service;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jsm.android.sporttour.app.data.Tournament;

import java.util.ArrayList;

/**
 * Created by jonathan on 8/11/16.
 */
public class MockTournamentRepositoryImpl  implements TournamentRepository{
    @Override
    public void getTournaments(@NonNull LoadTournamentsCallback callback) {
        callback.onTournamentsLoaded(new ArrayList<Tournament>());
    }

    @Override
    public void getTournament(@NonNull String tournamentName, @NonNull GetTournamentCallback callback) {

    }

    @Override
    public void saveTournament(@NonNull Tournament tournament) {

    }

    @Override
    public void getImage(String url, Object vh, @NonNull LoadImageCallback callback) {

    }

    @Override
    public void refreshData() {

    }
}
