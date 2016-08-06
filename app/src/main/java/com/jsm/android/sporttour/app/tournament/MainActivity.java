package com.jsm.android.sporttour.app.tournament;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsm.android.sporttour.app.R;
import com.jsm.android.sporttour.app.util.AuthUtil;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        if (null == savedInstanceState) {
            initFragment(TournamentFragment.newInstance());
        }
        //registerDeepLink();
    }

    private void initFragment(Fragment notesFragment) {
        // Add the NotesFragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, notesFragment);
        transaction.commit();
    }

    private void registerDeepLink(){
        Branch branch = Branch.getInstance();
        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked before showing up
                    Log.i(TAG, "deep link data: " + referringParams.toString());
                }
            }
        }, this.getIntent().getData(), this);
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        Log.d(TAG, "onNavigationItemSelected: Logging out");
                        AuthUtil.logout();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        menuItem.setChecked(false);
                        recreate();
                        break;
                    case R.id.exit:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("aguilaj10@gmail.com");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        mDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

}
