package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetails extends AppCompatActivity {

    private static final String log_tag = BookDetails.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


        Book book =
                (Book) getIntent().getParcelableExtra("book");

        Log.i(log_tag, "Book title " + book.getTitle());

        if (book != null) {
            TextView bookTitle =
                    (TextView) findViewById(R.id.book_title_info);

            bookTitle.setText(book.getTitle());

            Log.i(log_tag, "Book is not null: " + book.getTitle());
        } else {
            Log.i(log_tag, "Book is null");
        }
    }
}