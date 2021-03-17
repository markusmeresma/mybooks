package com.example.mybooks;

import android.os.AsyncTask;

public class SearchTask extends AsyncTask<String, Void, String> {

    private Utils utils = new Utils();


    @Override
    protected String doInBackground(String... urls) {

        utils.makeHttpRequest(utils.URLCreator(urls[0]));

        return null;
    }
}
