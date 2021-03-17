package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    public Utils () {}

    private String log_tag = Utils.class.getSimpleName();

    /**
     * Return string later
     * Make private later
     * @param url
     */
    public void makeHttpRequest (URL url)
    {
        HttpURLConnection connection;
        connection = null;
        int responseCode;

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            responseCode = connection.getResponseCode();
            Log.i(log_tag, "Making http request");

            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                Log.i(log_tag, "Http request successful with code " + responseCode);
            } else {
                Log.e(log_tag, "Http request failed with code " + responseCode);
            }
        } catch (IOException ex) {
            Log.e(log_tag, "Http connection error", ex);
            ex.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }

    /**
     * Make private later
     * @param urlString
     * @return
     */
    public URL URLCreator (String urlString)
    {
        try {
            URL url = new URL(urlString);
            Log.i(log_tag, "Url" + url);
            return url;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
