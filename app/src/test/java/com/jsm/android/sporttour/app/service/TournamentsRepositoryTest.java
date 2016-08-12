package com.jsm.android.sporttour.app.service;

import com.jsm.android.sporttour.app.helper.TestConstants;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 * Created by jonathan on 7/01/16.
 */
public class TournamentsRepositoryTest {

    private TournamentRepository tournamentRepository
            = Mockito.mock(MockTournamentRepositoryImpl.class);

    private TournamentRepository.LoadTournamentsCallback callback
            = Mockito.mock(TournamentRepository.LoadTournamentsCallback.class);

    private TournamentRepository.LoadImageCallback callbackImg
            = Mockito.mock(TournamentRepository.LoadImageCallback.class);

    private ArgumentCaptor<TournamentRepository.LoadTournamentsCallback> callbackCaptor
            = ArgumentCaptor.forClass(TournamentRepository.LoadTournamentsCallback.class);

    private ArgumentCaptor<TournamentRepository.LoadImageCallback> callbackCaptorImg
            = ArgumentCaptor.forClass(TournamentRepository.LoadImageCallback.class);

    @Test
    public void loadTournamentsTest(){
        loadCallsToRepository();

        Mockito.verify(tournamentRepository).
                getTournaments(
                        Mockito.any(TournamentRepository.LoadTournamentsCallback.class));

    }

    @Test
    public void getImageTest(){
        tournamentRepository.getImage(null, null, callbackImg);
        Mockito.verify(tournamentRepository).
                getImage(Mockito.any(String.class),
                        Mockito.anyObject(),
                        callbackCaptorImg.capture());

        Mockito.verify(tournamentRepository).
                getImage(Mockito.any(String.class),
                        Mockito.anyObject(),
                        Mockito.any(TournamentRepository.LoadImageCallback.class));

    }

    private void loadCallsToRepository(){
        tournamentRepository.getTournaments(callback);
        Mockito.verify(tournamentRepository).getTournaments(callbackCaptor.capture());
        callbackCaptor.getValue().onTournamentsLoaded(Arrays.asList(TestConstants.TOUR_LIST));

    }


}
