package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchInput = (EditText) findViewById(R.id.searchInput);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        // A variable to save user input
        String queryParams = searchInput.getText().toString();
        Log.i(getResources().getString(R.string.app_name), "User input " + queryParams);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTask searchTask = new SearchTask();
                // URL is broken
                String requestURL = searchTask.createURLString(queryParams);
                Log.i(getResources().getString(R.string.app_name), "URL " + requestURL);
                new SearchTask().execute(requestURL);
            }
        });


    }
}