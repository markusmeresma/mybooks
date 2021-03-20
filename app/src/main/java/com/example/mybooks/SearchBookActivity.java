package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchBookActivity extends AppCompatActivity {

    private RecyclerView fetchedBooksListView;
    String user_query = null;

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
        user_query = extras.getString("USER_QUERY");

        // Get the recyclerview through its id
        fetchedBooksListView = (RecyclerView) findViewById(R.id.fetched_books_list);

        // Fetch books with user query
        searchTask.execute(user_query);

        // Initialize books adapter
        BooksAdapter adapter = new BooksAdapter(this, R.layout.individual_book_entry, searchTask.fetched_books);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        fetchedBooksListView.setLayoutManager(layoutManager);

        fetchedBooksListView.setAdapter(adapter);
    }


}