package com.jsm.android.sporttour.app.tournament;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.auth.util.Preconditions;
import com.google.firebase.database.DatabaseError;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.SportTourApp;
import com.jsm.android.sporttour.app.data.Tournament;
import com.jsm.android.sporttour.app.service.TournamentRepository;
import com.jsm.android.sporttour.app.util.StaticConstants;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by admin on 7/25/2016.
 */
public class TournamentPresenter implements TournamentContract.UserActionListener {
    private static final String TAG = TournamentPresenter.class.getSimpleName();
    @Inject
    TournamentRepository tournamentRepository;
    private final TournamentContract.View tournamentView;

    public TournamentPresenter(@NonNull TournamentContract.View tournamentView) {
        this.tournamentView = tournamentView;
        injectDependencies();
    }

    public TournamentPresenter(@NonNull TournamentRepository tournamentRepository,
                               @NonNull TournamentContract.View tournamentView) {
        this.tournamentRepository = Preconditions.checkNotNull(tournamentRepository,
                StaticConstants.NULL_NOT_ALLOWED);
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

            @Override
            public void onErrorLoading(DatabaseError e) {
                Log.e(TAG, e.getDetails());
                tournamentView.setProgressIndicator(false);
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
            public void onImageLoaded(byte[] image, Object vh) {
                tournamentView.setProgressIndicator(false);
                tournamentView.showImage(image, (RecyclerView.ViewHolder) vh);
            }

            @Override
            public void onErrorLoading(Exception exception) {
                tournamentView.setProgressIndicator(false);
            }
        });
    }

    private void injectDependencies(){
        tournamentRepository = SportTourApp.getComponent().createTournamentRepository();
        SportTourApp.getComponent().inject(this);
    }
}
