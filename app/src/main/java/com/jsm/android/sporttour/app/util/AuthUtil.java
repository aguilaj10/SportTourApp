package com.jsm.android.sporttour.app.util;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by jonathan on 7/31/16.
 */
public class AuthUtil {
    private static final String TAG = AuthUtil.class.getSimpleName();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public static boolean isUserAuth(){
        if (user != null){
            return true;
        }else{
            return false;
        }
    }

    public static void logout(){
        FirebaseAuth.getInstance().signOut();
        user = null;
        Log.d(TAG, "logout: logged out");
    }

    public static FirebaseUser getUser(){
        return user;
    }
}
