package com.jsm.android.sporttour.app.tournament;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jsm.android.sporttour.app.data.Tournament;

import java.util.List;

/**
 * Created by admin on 7/25/2016.
 */
public interface TournamentContract {

    interface View{
        void setProgressIndicator(boolean active);
        void showTournament(List<Tournament> notes);
        void showImage(byte[] image, RecyclerView.ViewHolder tvh);
        void showAddTournament();
        void showTournamentDetail(String tournamentId);
        Context getContext();
    }

    interface UserActionListener{
        void loadTournaments();
        void addNewTournament();
        void openTournamentDetails(@NonNull Tournament tournament);
        void loadImage(String url, RecyclerView.ViewHolder vh);
    }
}
