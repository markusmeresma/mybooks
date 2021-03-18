package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

public class SearchTask extends AsyncTask<String, Void, String> {

    private Utils utils = new Utils();
    private static final String log_tag = SearchTask.class.getSimpleName();


    @Override
    protected String doInBackground(String... urls) {

        String requestedData;

        requestedData = utils.makeHttpRequest(utils.URLCreator(urls[0]));
        Log.i(log_tag, "Returned data: " + requestedData);

        return null;
    }
}
