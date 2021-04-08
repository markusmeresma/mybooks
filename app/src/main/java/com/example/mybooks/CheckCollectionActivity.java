package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CheckCollectionActivity extends ListActivity {

    DatabaseManipulator dm;
    List<String[]> books = null;
    String[] stg1;
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_collection);

        Button btnEmptyCollection = (Button) findViewById(R.id.btnEmptyList);
        Bundle extras = getIntent().getExtras();
        dm = new DatabaseManipulator(this);

        stg1 = getCollection(extras);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stg1);
        this.setListAdapter(adapter);
        selection = (TextView) findViewById(R.id.check_selection);

        btnEmptyCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.deleteCollection(extras.getString("COLLECTION"));
                Toast.makeText(getApplicationContext(), "Deleted collection", Toast.LENGTH_SHORT).show();

                stg1 = getCollection(extras);
                adapter.notifyDataSetChanged();
                selection = (TextView) findViewById(R.id.check_selection);
            }
        });
    }

    /**
     * Get collection from the db
     * @param extras
     * @return String[]
     */
    public String[] getCollection (Bundle extras)
    {
        if (extras.getString("COLLECTION").equals(getString(R.string.Favourites)))
        {
            books = dm.selectFavourites();

        } else if (extras.getString("COLLECTION").equals(getString(R.string.Want_to_Read)))
        {
            books = dm.selectWantToRead();

        } else if (extras.getString("COLLECTION").equals(getString(R.string.Currently_Reading)))
        {
            books = dm.selectCurrentlyReading();

        } else if (extras.getString("COLLECTION").equals(getString(R.string.Read)))
        {
            books = dm.selectRead();

        }

        stg1 = new String[books.size()];
        int x = 0;
        String stg;

        for (String[] book : books)
        {
            stg = book[1] + " - " + book[2];
            stg1[x] = stg;
            x++;
        }

        return stg1;
    }
}