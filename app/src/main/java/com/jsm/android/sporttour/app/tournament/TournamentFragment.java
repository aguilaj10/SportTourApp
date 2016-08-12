package com.jsm.android.sporttour.app.tournament;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.addtour.AddTourActivity;
import com.jsm.android.sporttour.app.common.AuthDialog;
import com.jsm.android.sporttour.app.data.Tournament;
import com.jsm.android.sporttour.app.service.TournamentRepositoryImpl;
import com.jsm.android.sporttour.app.util.AuthUtil;
import com.jsm.android.sporttour.app.util.FirebaseRecyclerAdapter;
import com.jsm.android.sporttour.app.util.StaticConstants;

import java.util.List;

public class TournamentFragment extends Fragment implements TournamentContract.View {
    private static final String TAG = TournamentFragment.class.getSimpleName();
    private TournamentContract.UserActionListener mListener;
    private FirebaseRecyclerAdapter<Tournament, TournamentViewHolder> mTourAdapter;
    private RecyclerView recycler;
    private FloatingActionButton mAddTour;
    private ProgressDialog progressDialog;


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

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getActivity().getResources().getString(R.string.progress_dialog_loading));
        progressDialog.setIndeterminate(true);
    }

    class AddTourListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(AuthUtil.isUserAuth()){
                Log.d(TAG, "onClick: " + AuthUtil.getUser());
                Intent i = new Intent(getContext(), AddTourActivity.class);
                getActivity().startActivity(i);
            }else{
                AuthDialog dialog = new AuthDialog();
                dialog.show(getActivity().getFragmentManager(), TAG);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StaticConstants.RC_SIGN_IN) {
            if (resultCode == getActivity().RESULT_OK) {
                Log.d(TAG, "onActivityResult: " + data);
            } else {
                Log.e("Firebase -->", ""+resultCode);
            }
        }
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

        mAddTour = (FloatingActionButton) root.findViewById(R.id.tour_add);
        mAddTour.setOnClickListener(new AddTourListener());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if(active)
            progressDialog.show();
        else
            progressDialog.hide();
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
