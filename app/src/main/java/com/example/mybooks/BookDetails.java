package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

            bookTitle.setText(book.getTitle());
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
                    saveToDatabase(book, getString(R.string.Favourites));

                } else if (selectedCollection.equals(getString(R.string.add_to_want_to_read)))
                {
                    saveToDatabase(book, getString(R.string.Want_to_Read));

                } else if (selectedCollection.equals(getString(R.string.add_to_currently_reading)))
                {
                    saveToDatabase(book, getString(R.string.Currently_Reading));

                } else if (selectedCollection.equals(getString(R.string.add_to_read)))
                {
                    saveToDatabase(book, getString(R.string.Read));

                }

            }
        });
    }

    public void saveToDatabase (Book book, String selectedCollection)
    {
        String title = book.getTitle();
        // Add author and description
        String collection = selectedCollection;
        this.dm = new DatabaseManipulator(this);
        this.dm.insert(title, collection);
    }
}