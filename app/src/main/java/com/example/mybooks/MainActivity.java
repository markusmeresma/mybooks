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

        final EditText searchInput = (EditText) findViewById(R.id.searchInput);
        Button searchButton = (Button) findViewById(R.id.searchButton);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuery = searchInput.getText().toString();
                // For debugging purposes
                Log.i(getResources().getString(R.string.app_name), "userQuery " + userQuery);

                new SearchTask().execute(userQuery);
            }
        });


    }
}