package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchTask extends AsyncTask<String, Void, String> {

    private Utils utils = new Utils();
    private static final String log_tag = SearchTask.class.getSimpleName();

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
        Log.i(log_tag, "JSON response: " + JSONString);

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
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            // Set iterator
            int i = 0;

            while (i < jsonArray.length()) {
                JSONObject bookJSON = jsonArray.getJSONObject(i);
                JSONObject volumeInfo = bookJSON.getJSONObject("volumeInfo");

                // Try to create a new book
                try {
                    // Create new Book
                    Book book = new Book();
                    // Set book ID
                    book.setId(volumeInfo.getString("id"));
                    // Set book title
                    book.setTitle(volumeInfo.getString("title"));
                    Log.i(log_tag, "ID: " + book.getId() + "; Title: " + book.getTitle() + "\n");

                    // Save book to array of books

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
