package com.jsm.android.sporttour.app.tournament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.data.Tournament;
import com.jsm.android.sporttour.app.service.TournamentRepositoryImpl;
import com.jsm.android.sporttour.app.util.FirebaseRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class TournamentFragment extends Fragment implements TournamentContract.View {
    private static final String TAG = TournamentFragment.class.getSimpleName();
    private TournamentContract.UserActionListener mListener;
    private FirebaseRecyclerAdapter<Tournament, TournamentViewHolder> mTourAdapter;
    private RecyclerView recycler;


    public TournamentFragment() {
        // Required empty public constructor
    }

    public static TournamentFragment newInstance(){
        return new TournamentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener = new TournamentPresenter(TournamentRepositoryImpl.getInstance(),this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener.loadTournaments();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tournament, container, false);
        //Get the recycler view from the activity main xml file
        recycler = (RecyclerView) root.findViewById(R.id.tournament_list);
        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showTournament(final List<Tournament> tournaments) {
        Log.d(TAG, "showTournament: " + tournaments.toString());
        mTourAdapter = new FirebaseRecyclerAdapter<Tournament, TournamentViewHolder>
                (Tournament.class,R.layout.tournament_card,TournamentViewHolder.class,
                        tournaments.toArray(new Tournament[tournaments.size()])) {
            @Override
            protected void populateViewHolder(TournamentViewHolder viewHolder, Tournament model, int position) {
                viewHolder.tourName.setText(model.getName());
                viewHolder.tourSport.setText(model.getSport());
                viewHolder.tourPlace.setText(model.getPlace());
                mListener.loadImage(model.getLogo(),viewHolder);
            }
        };
        recycler.setAdapter(mTourAdapter);
    }

    @Override
    public void showImage(byte[] image, RecyclerView.ViewHolder placeHolder) {
        if(placeHolder instanceof TournamentViewHolder)
            Glide.with(getContext())
                .load(image)
                .asBitmap()
                .into(((TournamentViewHolder)placeHolder).tourLogo);
    }

    @Override
    public void showAddTournament() {

    }

    @Override
    public void showTournamentDetail(String tournamentId) {

    }

    public static class TournamentViewHolder extends RecyclerView.ViewHolder{
        public ImageView tourLogo;
        public TextView tourName;
        public TextView tourSport;
        public TextView tourPlace;
        public View tourFav;

        public TournamentViewHolder(View itemView) {
            super(itemView);
            tourLogo = (ImageView) itemView.findViewById(R.id.tour_img);
            tourName = (TextView) itemView.findViewById(R.id.tour_name);
            tourSport = (TextView) itemView.findViewById(R.id.sport);
            tourPlace = (TextView) itemView.findViewById(R.id.tour_place);
            tourFav = itemView.findViewById(R.id.tour_fav);
        }
    }
}
