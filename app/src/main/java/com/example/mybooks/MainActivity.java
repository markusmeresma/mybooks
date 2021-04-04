package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchInput = (EditText) findViewById(R.id.searchInput);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuery = searchInput.getText().toString();
                // For debugging purposes
                Log.i(getResources().getString(R.string.app_name), "userQuery " + userQuery);

                // Start a new activity to fetch books
                Intent searchBookActivity = new Intent(MainActivity.this, SearchBookActivity.class);
                searchBookActivity.putExtra("USER_QUERY", userQuery);
                startActivity(searchBookActivity);
            }
        });

        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked on menu", Toast.LENGTH_LONG).show();
            }
        });


    }
}