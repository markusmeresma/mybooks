package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

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
        Log.i(log_tag, "Returned data: " + JSONString);

        return JSONString;
    }

    /**
     *
     * @param JSONString
     */
    @Override
    protected void onPostExecute (String JSONString)
    {
        // Create JSON object array here
    }
}
