package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        Bundle extras = getIntent().getExtras();
        SearchTask searchTask = new SearchTask();

        // If array of fetched books is not empty then clear it
        if (searchTask.fetched_books.size() > 0) {
            searchTask.fetched_books.clear();
        }

        // Get the user query
        String user_query = extras.getString("USER_QUERY");

        // Fetch books with user query
        searchTask.execute(user_query);
    }
}