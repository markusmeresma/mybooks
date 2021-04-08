package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetails extends AppCompatActivity {

    private static final String log_tag = BookDetails.class.getSimpleName();
    private DatabaseManipulator dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Book book =
                (Book) getIntent().getSerializableExtra("book");

        Log.i(log_tag, "Book title " + book.getTitle());

        if (book != null) {
            TextView bookTitle =
                    (TextView) findViewById(R.id.book_title_info);

            TextView bookSubtitle =
                    (TextView) findViewById(R.id.book_subtitle_info);

            TextView bookAuthors =
                    (TextView) findViewById(R.id.book_authors_info);

            TextView bookDescription =
                    (TextView) findViewById(R.id.book_description_info);

            bookTitle.setText(book.getTitle());
            bookSubtitle.setText(book.getSubtitle());
            bookAuthors.setText(book.getAuthors());
            bookDescription.setText(book.getDescription());
        }

        Button btnAddToCollection = (Button) findViewById(R.id.btnAddToCollection);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.add_to_collection_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Get selected item from the spinner and add the book to the selected collection
        btnAddToCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCollection = spinner.getSelectedItem().toString();

                // Not a very elegant solution to use if..else but could't get the string comparison working with switch..case statements
                if (selectedCollection.equals(getString(R.string.add_to_favourites)))
                {
                    try {
                        saveToDatabase(book, getString(R.string.Favourites));
                        Toast.makeText(getApplicationContext(), "Saved to favourites", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException ex) {
                        ex.printStackTrace();
                        Toast.makeText(BookDetails.this, "Unable to add the book to the collection", Toast.LENGTH_SHORT).show();
                    }

                } else if (selectedCollection.equals(getString(R.string.add_to_want_to_read)))
                {
                    try {
                        saveToDatabase(book, getString(R.string.Want_to_Read));
                        Toast.makeText(getApplicationContext(), "Saved to Want to Read", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException ex) {
                        ex.printStackTrace();
                        Toast.makeText(BookDetails.this, "Unable to add the book to the collection", Toast.LENGTH_SHORT).show();
                    }

                } else if (selectedCollection.equals(getString(R.string.add_to_currently_reading)))
                {
                    try {
                        saveToDatabase(book, getString(R.string.Currently_Reading));
                        Toast.makeText(getApplicationContext(), "Saved to Currently Reading", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException ex) {
                        ex.printStackTrace();
                        Toast.makeText(BookDetails.this, "Unable to add the book to the collection", Toast.LENGTH_SHORT).show();
                    }

                } else if (selectedCollection.equals(getString(R.string.add_to_read)))
                {
                    try {
                        saveToDatabase(book, getString(R.string.Read));
                        Toast.makeText(getApplicationContext(), "Saved to Read", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException ex) {
                        ex.printStackTrace();
                        Toast.makeText(BookDetails.this, "Unable to add the book to the collection", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    /**
     * Save book to the db
     * @param book
     * @param selectedCollection
     */
    public void saveToDatabase (Book book, String selectedCollection)
    {
        String title = book.getTitle();
        String authors = book.getAuthors();
        String subtitle = book.getSubtitle();
        String description = book.getDescription();
        String collection = selectedCollection;
        this.dm = new DatabaseManipulator(this);
        this.dm.insert(title, authors, subtitle, description, collection);
    }
}