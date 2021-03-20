package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

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
            // Get the JSON array of books
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");

            Log.i(log_tag, "JSONArray " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject bookJSON = (JSONObject) jsonArray.get(i);
                JSONObject volumeInfo = bookJSON.getJSONObject("volumeInfo");

                Log.i(log_tag, "bookJSON: " + bookJSON);
                Log.i(log_tag, "volumeInfo: " + volumeInfo);

                try {
                    // Create a new book
                    Book book = new Book();
                    // Set book title
                    book.setTitle(volumeInfo.getString("title"));
                    // For debugging
                    Log.i(log_tag, "Title: " + book.getTitle());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
