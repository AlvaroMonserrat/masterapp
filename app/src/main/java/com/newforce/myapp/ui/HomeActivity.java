package com.newforce.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.newforce.myapp.R;
import com.newforce.myapp.ui.activities.AccountActivity;
import com.newforce.myapp.ui.activities.RegionActivity;
import com.newforce.myapp.auth.RegisterActivity;
import com.newforce.myapp.ui.fragments.CreateFragment;
import com.newforce.myapp.ui.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    HomeFragment.onFragmentBtnSelected{


    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = HomeActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        // Load default fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new HomeFragment());
        fragmentTransaction.commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new HomeFragment());
            fragmentTransaction.commit();
            Log.i(LOG_TAG, "Home Selected: " + ACTIVITY_TAG);
        }

        if(menuItem.getItemId() == R.id.create){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new CreateFragment());
            fragmentTransaction.commit();
            Log.i(LOG_TAG, "Create Selected: " + ACTIVITY_TAG);
        }

        if(menuItem.getItemId() == R.id.account){
            Log.i(LOG_TAG, "Create Selected: " + ACTIVITY_TAG);
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        }

        if(menuItem.getItemId() == R.id.regions){
            Log.i(LOG_TAG, "Create Selected: " + ACTIVITY_TAG);
            Intent intent = new Intent(this, RegionActivity.class);
            startActivity(intent);
        }

        return true;
    }


    @Override
    public void onButtonSelected() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, new CreateFragment());
        fragmentTransaction.commit();
        Log.i(LOG_TAG, "Create Selected: " + ACTIVITY_TAG);
    }
}
