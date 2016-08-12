package com.jsm.android.sporttour.app.helper;

import com.jsm.android.sporttour.app.data.Tournament;

/**
 * Created by jonathan on 8/11/16.
 */
public class TestConstants {
    public static final Tournament[] TOUR_LIST = new Tournament[2];
    static{
        TOUR_LIST[0] = new Tournament(null, null, "Tournament 1", "Atlanta", "Soccer");
        TOUR_LIST[1] = new Tournament(null, null, "Tournament 1", "Seattle", "Basketball");
    }

    public static final byte[] IMAGE = new byte[]{1,2,3,4,5,6,7};
}