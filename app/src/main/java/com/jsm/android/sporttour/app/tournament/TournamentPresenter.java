package com.jsm.android.sporttour.app.tournament;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.auth.util.Preconditions;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.data.Tournament;
import com.jsm.android.sporttour.app.service.TournamentRepository;

import java.util.List;

/**
 * Created by admin on 7/25/2016.
 */
public class TournamentPresenter implements TournamentContract.UserActionListener {
    private static final String TAG = TournamentPresenter.class.getSimpleName();
    private final TournamentRepository tournamentRepository;
    private final TournamentContract.View tournamentView;

    public TournamentPresenter(@NonNull TournamentRepository tournamentRepository,
                               @NonNull TournamentContract.View tournamentView) {
        this.tournamentRepository = Preconditions.checkNotNull(tournamentRepository,
                tournamentView.getContext().getString(R.string.no_null_value_allowed));
        this.tournamentView = tournamentView;
    }

    @Override
    public void loadTournaments() {
        tournamentView.setProgressIndicator(true);
        tournamentRepository.getTournaments(new TournamentRepository.LoadTournamentsCallback(){
            @Override
            public void onTournamentsLoaded(List<Tournament> tournaments){
                tournamentView.setProgressIndicator(false);
                Log.d(TAG, "onTournamentsLoaded: " + tournaments.toString());
                tournamentView.showTournament(tournaments);
            }
        });
    }

    @Override
    public void addNewTournament() {

    }

    @Override
    public void openTournamentDetails(@NonNull Tournament tournament) {

    }

    @Override
    public void loadImage(String url, RecyclerView.ViewHolder vh) {
        tournamentView.setProgressIndicator(true);
        tournamentRepository.getImage(url, vh, new TournamentRepository.LoadImageCallback() {
            @Override
            public void onImageLoaded(byte[] image, RecyclerView.ViewHolder vh) {
                tournamentView.showImage(image, vh);
            }
        });
    }
}
