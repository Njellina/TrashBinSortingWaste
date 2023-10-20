package com.example.trashbinproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    UserHomeFragment UserHomeFragment = new UserHomeFragment();
    UserListFragment UserListFragment = new UserListFragment();
    UserProfileFragment UserProfileFragment = new UserProfileFragment();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, UserHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, UserHomeFragment).commit();
                        return true;
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, UserListFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, UserProfileFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

}