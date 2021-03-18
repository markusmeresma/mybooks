package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

public class SearchTask extends AsyncTask<String, Void, String> {

    private Utils utils = new Utils();
    private static final String log_tag = SearchTask.class.getSimpleName();

    /*
    public String createURLString (String requestParameters)
    {
        String stringURL;

        // Something is broken here - try using uri in utils class to build up the url instead of this approach
        // Build URL string with search parameters given by the user
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GOOGLE_BOOKS_API + requestParameters);

        stringURL = stringBuilder.toString();

        Log.i(log_tag, "stringURL = " + stringURL);

        return stringURL;
    }
    */

    @Override
    protected String doInBackground(String... urls) {

        // This method is currently used for debugging

        String requestedData;

        requestedData = utils.makeHttpRequest(urls[0]);
        Log.i(log_tag, "Returned data: " + requestedData);

        return null;
    }
}
