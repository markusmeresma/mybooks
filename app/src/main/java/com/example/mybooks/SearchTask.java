package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchTask extends AsyncTask<String, Void, ArrayList<Book>> {

    private Utils utils = new Utils();
    private static final String log_tag = SearchTask.class.getSimpleName();

    /**
     *
     * @param urls
     * @return
     */
    @Override
    protected ArrayList<Book> doInBackground (String... urls) {

        String JSONString;
        ArrayList<Book> fetched_books = new ArrayList<>();

        // Make API request
        JSONString = utils.makeHttpRequest(urls[0]);

        // Convert response string to an array of Book objects
        try {
            // Convert the raw response string to JSON object
            JSONObject jsonObject = new JSONObject(JSONString);
            // Get the JSON array of books
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");

            Log.i(log_tag, "JSONArray " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject bookJSON = (JSONObject) jsonArray.get(i);
                JSONObject volumeInfo = bookJSON.getJSONObject("volumeInfo");

                try {
                    // Create a new book
                    Book book = new Book();
                    book.setTitle(volumeInfo.getString("title"));

                    // If the book has a subtitle then attach it to the object
                    if (volumeInfo.has("subtitle")) {
                        book.setSubtitle(volumeInfo.getString("subtitle"));
                    } else {
                        book.setSubtitle("Not available");
                    }

                    // If the book has authors then attach them to the object
                    String authors = "";
                    if (volumeInfo.has("authors")) {
                        // Get the authors array
                        JSONArray authorsArray = volumeInfo.getJSONArray("authors");

                        for (int j = 0; j < authorsArray.length(); j++) {
                            if (j == 0) {
                                authors += authorsArray.getString(j);
                            } else {
                                authors += " , " + authorsArray.getString(j);
                            }
                        }
                        book.setAuthors(authors);
                    } else {
                        book.setAuthors("Not available");
                    }

                    // If the book has a description then attach it to the object
                    if (volumeInfo.has("description")) {
                        book.setDescription(volumeInfo.getString("description"));
                    } else {
                        book.setDescription("Not available");
                    }

                    // Add fetched book to list
                    fetched_books.add(book);
                    // For debugging
                    Log.i(log_tag, "Title: " + book.getTitle());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fetched_books;
    }
}
