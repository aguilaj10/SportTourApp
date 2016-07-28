package com.jsm.android.sporttour.app.service;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jsm.android.sporttour.app.data.Tournament;

import java.util.List;

/**
 * Created by admin on 7/25/2016.
 */
public interface TournamentRepository {
    interface LoadTournamentsCallback{
        void onTournamentsLoaded(List<Tournament> tournaments);
    }

    interface GetTournamentCallback{
        void onTournamentLoaded(Tournament tournament);
    }

    interface LoadImageCallback{
        void onImageLoaded(byte[] image, RecyclerView.ViewHolder vh);
    }

    void getTournaments(@NonNull LoadTournamentsCallback callback);

    void getTournament(@NonNull String tournamentName, @NonNull GetTournamentCallback callback);

    void saveTournament(@NonNull Tournament tournament);

    void getImage(String url, RecyclerView.ViewHolder vh, @NonNull LoadImageCallback callback);

    void refreshData();
}
