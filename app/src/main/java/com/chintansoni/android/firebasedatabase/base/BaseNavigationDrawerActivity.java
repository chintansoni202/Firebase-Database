package com.chintansoni.android.firebasedatabase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.chintansoni.android.firebasedatabase.R;
import com.chintansoni.android.firebasedatabase.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public abstract class BaseNavigationDrawerActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.dl_home)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_home);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mi_home_logout) {
            // Handle the camera action
            FirebaseAuth.getInstance().signOut();
            finish();
            launchActivity(LoginActivity.class);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
