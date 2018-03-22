
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:27 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;




import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @SuppressWarnings("Empty")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Bundle bundle = new Bundle();

        if (id == R.id.nav_nowplaying) {
            FragmentNowPlaying nowplayingFragment = new FragmentNowPlaying();
            getFragment(nowplayingFragment, getResources().getString(R.string.app_name));
        } else if (id == R.id.nav_upcoming) {
            FragmentUpComing upComingFragment = new FragmentUpComing();
            getFragment(upComingFragment, getResources().getString(R.string.app_name));
        } else if (id == R.id.nav_favorite){
            FragmentFavorite favoriteFragment = new FragmentFavorite();
            getFragment(favoriteFragment, getResources().getString(R.string.app_name));
        }

        DrawerLayout appsdrawer = (DrawerLayout) findViewById(R.id.app_drawer);
        appsdrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout appsdrawer = (DrawerLayout) findViewById(R.id.app_drawer);
        ActionBarDrawerToggle bardrawer = new ActionBarDrawerToggle(
                this, appsdrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        appsdrawer.addDrawerListener(bardrawer);
        bardrawer.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menuview);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null ){
            FragmentNowPlaying nowplayingFragment = new FragmentNowPlaying();
            getFragment(nowplayingFragment, getResources().getString(R.string.app_name));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.openBrowser:
                Intent browserIntent = new Intent(NavigationActivity.this, MainActivity.class);

                startActivity(browserIntent);

                return true;
            case R.id.action_settings:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout appsdrawer = (DrawerLayout) findViewById(R.id.app_drawer);
        if (appsdrawer.isDrawerOpen(GravityCompat.START)) {
            appsdrawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Apakah Anda Yakin Ingin Keluar Aplikasi?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            NavigationActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }
    }

    private void getFragment(Fragment fragmentTitle, String ActionBarTitle){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation, fragmentTitle, fragmentTitle.getClass().getSimpleName()).commit();
        getSupportActionBar().setTitle(ActionBarTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        getMenuInflater().inflate(R.menu.search_menu, menu);

        return true;
    }

}
