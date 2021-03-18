package com.example.mybooks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    private static final String log_tag = Utils.class.getSimpleName();

    public Utils () {}

    /**
     * Return string later
     * Make private later
     * @param url
     * @retun String
     */
    public String makeHttpRequest (URL url)
    {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        int responseCode;
        String returnedJSONString = null;

        try {
            // Open the network connection
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Used for debugging
            responseCode = connection.getResponseCode();

            // Used for debugging
            Log.i(log_tag, "Making http request");

            if (responseCode == 200) {
                // Used for debugging
                Log.i(log_tag, "Http request successful with code " + responseCode);

                InputStream inputStream = connection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    // Read lines from the bufferedReader
                    stringBuilder.append(line + "\n");
                }

                // If stream was empty
                if (stringBuilder.length() == 0) {
                    return null;
                }

                returnedJSONString = stringBuilder.toString();

            } else {
                Log.e(log_tag, "Http request failed with code " + responseCode);
            }
        } catch (IOException ex) {
            Log.e(log_tag, "Http connection error", ex);
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return returnedJSONString;

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
