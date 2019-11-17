package com.example.notanotherweatherapp.Helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

import javax.net.ssl.HttpsURLConnection;

public class Helper {
    static String stream = null;

    public Helper(){

    }

    public String getHttpData(String urlString){
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader r = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = r.readLine()) != null)
                    sb.append(line);

                stream = sb.toString();
                httpURLConnection.disconnect();
            }

            else if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
                stream = "ERROR: City Not Found";
                httpURLConnection.disconnect();
            }

            else{
                stream = "ERROR: Could Not Establish Connection";
                httpURLConnection.disconnect();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return stream;
    }

}
