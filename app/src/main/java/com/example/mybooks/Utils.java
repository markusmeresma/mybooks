package com.example.mybooks;

import android.net.Uri;
import android.os.AsyncTask;
import android.service.autofill.AutofillService;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Utils {

    private static final String log_tag = Utils.class.getSimpleName();

    public Utils () {}

    /**
     * Return string later
     * Make private later
     * @param
     * @retun String
     */
    public String makeHttpRequest (String userQuery)
    {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        int responseCode;
        String JSONString = null;
        URL url = null;

        try {
            // Create URL
            url = createURL(userQuery);

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

                JSONString = stringBuilder.toString();

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

        return JSONString;

    }

    /**
     * Make private later
     * @param userQuery
     * @return
     */
    public URL createURL (String userQuery)
    {
        final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?";
        final String QUERY_PARAM = "q";

        try {
            // Build up the query URI
            Uri builtURI = Uri.parse(GOOGLE_BOOKS_API).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, userQuery)
                    .build();

            // Create URL
            URL url = new URL(builtURI.toString());

            return url;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
