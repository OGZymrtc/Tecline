package com.ogzymrtc.tecline.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ogzymrtc.tecline.Fragments.FavoritesFragment;
import com.ogzymrtc.tecline.Fragments.SourcesFragment;
import com.ogzymrtc.tecline.Fragments.TopStoriesFragment;
import com.ogzymrtc.tecline.R;

public class FeedActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        firebaseAuth = FirebaseAuth.getInstance();
        navigationBar = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new TopStoriesFragment()).commit();
        navigationBar.setOnNavigationItemSelectedListener(navlistener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.topStoriesButton:
                            selectedFragment = new TopStoriesFragment();
                            break;
                        case R.id.sourcesButton:
                            selectedFragment = new SourcesFragment();
                            break;
                        case R.id.logoutButton:
                            firebaseAuth.signOut();
                            Intent intent = new Intent(FeedActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case R.id.favNewsButton:
                            selectedFragment = new FavoritesFragment();
                            break;
                    }
                    if (selectedFragment != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
                    return true;
                }
            };

}