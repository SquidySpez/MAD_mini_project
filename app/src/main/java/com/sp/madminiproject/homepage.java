package com.sp.madminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize drawer and navigation view
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Create ActionBarDrawerToggle and set it to the DrawerLayout
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        drawerLayout.addDrawerListener(drawerToggle);

        // Enable the Up button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerToggle.syncState();

        if (drawerLayout != null && navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Handle navigation view item clicks here
                    int itemId = item.getItemId();

                    if (itemId == R.id.draw_calendar) {
                        replaceFragment(new CalendarFragment());
                        updateBottomNavigation(itemId);

                    } else if (itemId == R.id.draw_reminder) {
                        replaceFragment(new ReminderFragment());
                        updateBottomNavigation(itemId);

                    } else if (itemId == R.id.draw_social) {
                        replaceFragment(new SocialFragment());
                        updateBottomNavigation(itemId);

                    } else if (itemId == R.id.draw_profile) {
                        replaceFragment(new ProfileFragment());
                        updateBottomNavigation(itemId);

                    } else if (itemId == R.id.draw_settings) {
                        startActivity(new Intent(homepage.this, SettingsFragment.class));

                    } else if (itemId == R.id.draw_logout) {
                        finish();

                    }

                    // Close the drawer after handling the item click
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            });
        }

        // Set up bottom navigation
        bottomNavigationView = findViewById(R.id.bottommenu);
        replaceFragment(new HomeFragment());
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle bottom navigation item clicks here
                int itemId = item.getItemId();

                if (itemId == R.id.calendar) {
                    replaceFragment(new CalendarFragment());
                } else if (itemId == R.id.reminder) {
                    replaceFragment(new ReminderFragment());
                } else if (itemId == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == R.id.social) {
                    replaceFragment(new SocialFragment());
                } else if (itemId == R.id.profile) {
                    replaceFragment(new ProfileFragment());
                }

                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            // Handle other action bar items as needed
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateBottomNavigation(int itemId) {
        if (itemId == R.id.draw_calendar) {
            bottomNavigationView.setSelectedItemId(R.id.calendar);
        } else if (itemId == R.id.draw_reminder) {
            bottomNavigationView.setSelectedItemId(R.id.reminder);
        } else if (itemId == R.id.draw_social) {
            bottomNavigationView.setSelectedItemId(R.id.social);
        } else if (itemId == R.id.draw_profile) {
            bottomNavigationView.setSelectedItemId(R.id.profile);
        }

    }




}



