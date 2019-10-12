package com.example.notanotherweatherapp.Helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class Helper {
    static String stream = null;

    public Helper(){

    }

    public String getHttpData(String urlString){
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            if(httpURLConnection.getResponseCode() == 200)
            {
                BufferedReader r = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = r.readLine()) != null)
                    sb.append(line);

                stream = sb.toString();
                httpURLConnection.disconnect();

            }
            else{
                Log.e("TAG", "Connection could not be established, status code: " + httpURLConnection.getResponseCode());
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
