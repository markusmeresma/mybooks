package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchTask extends AsyncTask<String, Void, String> {

    private Utils utils = new Utils();
    private static final String log_tag = SearchTask.class.getSimpleName();
    public ArrayList<Book> fetched_books = new ArrayList<>();

    /**
     *
     * @param urls
     * @return JSONString
     */
    @Override
    protected String doInBackground (String... urls) {

        String JSONString;

        JSONString = utils.makeHttpRequest(urls[0]);
        // Used for debugging purposes
        // Log.i(log_tag, "JSON response: " + JSONString);

        return JSONString;
    }

    /**
     *
     * @param JSONString
     */
    @Override
    protected void onPostExecute (String JSONString)
    {
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
                    // Set book title
                    book.setTitle(volumeInfo.getString("title"));
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

        // For debugging
        Log.i(log_tag, "Fetched books array size: " + fetched_books.size());

    }
}
