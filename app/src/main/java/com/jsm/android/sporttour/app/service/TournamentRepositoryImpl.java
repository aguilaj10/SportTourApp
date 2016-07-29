package com.jsm.android.sporttour.app.service;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jsm.android.sporttour.app.data.Tournament;
import com.jsm.android.sporttour.app.util.ChildEnum;
import com.jsm.android.sporttour.app.util.StaticConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/25/2016.
 */
public class TournamentRepositoryImpl implements TournamentRepository {
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final String TAG = TournamentRepositoryImpl.class.getName();
    private static TournamentRepositoryImpl INSTANCE = null;

    private TournamentRepositoryImpl(){

    }

    public static TournamentRepository getInstance(){
        if(INSTANCE == null)
            INSTANCE = new TournamentRepositoryImpl();
        return INSTANCE;
    }

    @Override
    public void getTournaments(@NonNull final LoadTournamentsCallback callback) {
        Query query = mRoot.child(ChildEnum.TOURNAMENTS.getChildName());
        final List<Tournament> tournaments = new ArrayList<>();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    final Tournament t = child.getValue(Tournament.class);
                    tournaments.add(t);
                }
                callback.onTournamentsLoaded(tournaments);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void getTournament(@NonNull String tournamentName, @NonNull GetTournamentCallback callback) {

    }

    @Override
    public void saveTournament(@NonNull Tournament tournament) {

    }

    @Override
    public void getImage(String url, final RecyclerView.ViewHolder vh,
                         @NonNull final LoadImageCallback callback) {
        StorageReference storageRef = storage.getReferenceFromUrl(url);
        storageRef.getBytes(StaticConstants.ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        callback.onImageLoaded(bytes, vh);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.wtf(TAG, "onFailure: " + exception.getMessage());
                    }
                });
    }

    @Override
    public void refreshData() {

    }
}
