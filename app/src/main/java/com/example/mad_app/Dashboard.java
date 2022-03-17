package com.example.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set home fragment as the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.home_nav:
                        fragment = new HomeFragment();
                        break;
                    case R.id.news_nav:
                        fragment = new NewsFragment();
                        break;
                    case R.id.add_nav:
                        fragment = new AddFragment();
                        break;
                    case R.id.jobs_nav:
                        fragment = new JobsFragment();
                        break;
                    case R.id.profile_nav:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, fragment).commit();

                return true;
            }
        });
    }

}