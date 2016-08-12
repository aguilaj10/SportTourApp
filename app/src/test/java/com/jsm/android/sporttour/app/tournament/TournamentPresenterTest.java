package com.jsm.android.sporttour.app.tournament;

import android.support.v7.widget.RecyclerView;

import com.jsm.android.sporttour.app.helper.TestConstants;
import com.jsm.android.sporttour.app.service.MockTournamentRepositoryImpl;
import com.jsm.android.sporttour.app.service.TournamentRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

/**
 * Created by jonathan on 7/11/16.
 */
public class TournamentPresenterTest {
    @Mock
    MockTournamentRepositoryImpl tournamentRepository;

    @Mock
    TournamentContract.View tournamentView;

    @Mock
    RecyclerView.ViewHolder viewHolder;

    TournamentPresenter tournamentPresenter;

    @Captor
    ArgumentCaptor<TournamentRepository.LoadTournamentsCallback> callbackCaptor;
    @Captor
    ArgumentCaptor<TournamentRepository.LoadImageCallback> callbackCaptorImg;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        tournamentPresenter = new TournamentPresenter(tournamentRepository,tournamentView);
    }

    @Test
    public void loadTournamentsTest(){
        tournamentPresenter.loadTournaments();
        Mockito.verify(tournamentRepository).getTournaments(callbackCaptor.capture());

        //Trigger the callback
        callbackCaptor.getValue().onTournamentsLoaded(Arrays.asList(TestConstants.TOUR_LIST));

        Mockito.verify(tournamentView).setProgressIndicator(false);
        Mockito.verify(tournamentView).showTournament(Arrays.asList(TestConstants.TOUR_LIST));
    }

    @Test
    public void getImageTest(){
        tournamentPresenter.loadImage(Mockito.any(String.class),
                viewHolder);
        Mockito.verify(tournamentView).setProgressIndicator(true);
        Mockito.verify(tournamentRepository).getImage(Mockito.any(String.class),
                Mockito.anyObject(), callbackCaptorImg.capture());

        //Trigger the callback
        callbackCaptorImg.getValue().onImageLoaded(TestConstants.IMAGE, viewHolder);

        //Verify methods were called
        Mockito.verify(tournamentView).setProgressIndicator(false);
        Mockito.verify(tournamentView).showImage(TestConstants.IMAGE,
                viewHolder);
    }
}
