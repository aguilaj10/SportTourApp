package com.jsm.android.sporttour.app.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.util.StaticConstants;

/**
 * Created by jonathan on 7/31/16.
 */
public class AuthDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.user_no_auth)
                .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivityForResult(
                                AuthUI.getInstance()
                                        .createSignInIntentBuilder()
                                        .setProviders(
                                                AuthUI.EMAIL_PROVIDER,
                                                AuthUI.GOOGLE_PROVIDER,
                                                AuthUI.FACEBOOK_PROVIDER)
                                        .build(),
                                StaticConstants.RC_SIGN_IN);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
