package com.example.mybooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new SearchFragment();
    final Fragment fragment2 = new CollectionsFragment();
    final Fragment fragment3 = new MoreFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main, fragment3).hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main, fragment2).hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main, fragment1).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // change fragments
            switch (item.getItemId()) {
                case R.id.search:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragment1)
                            .commit();
                    active = fragment1;
                    return true;

                case R.id.collections:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragment2)
                            .commit();
                    active = fragment2;
                    return true;

                case R.id.more:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragment3)
                            .commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };
}