package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchBookActivity extends AppCompatActivity {

    private RecyclerView fetchedBooksListView;
    private String user_query = null;
    public ArrayList<Book> fetched_books = new ArrayList<>();
    private  static final String log_tag = SearchBookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        // Get the recyclerview through its id
        fetchedBooksListView = (RecyclerView) findViewById(R.id.booksListView);

        fetchedBooksListView.setHasFixedSize(true);

        Bundle extras = getIntent().getExtras();
        SearchTask searchTask = new SearchTask();

        // Get the user query
        user_query = extras.getString("USER_QUERY");

        try {
            // Fetch books
            fetched_books = searchTask.execute(user_query).get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        // For debugging
        Log.i(log_tag, "Inside Search Book Activity fetched books list size: " + fetched_books.size());

        // Initialize books adapter
        BooksAdapter adapter = new BooksAdapter(this, R.layout.book_entry, fetched_books);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        fetchedBooksListView.setLayoutManager(layoutManager);

        fetchedBooksListView.setAdapter(adapter);
    }
}