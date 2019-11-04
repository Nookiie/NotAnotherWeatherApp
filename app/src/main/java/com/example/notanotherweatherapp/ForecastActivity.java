package com.example.notanotherweatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notanotherweatherapp.Common.Common;
import com.example.notanotherweatherapp.Helper.Helper;
import com.example.notanotherweatherapp.Model.OpenForecastMap;
import com.example.notanotherweatherapp.Model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ForecastActivity extends AppCompatActivity {

    List<TextView> days = new List<TextView>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<TextView> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(TextView textView) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends TextView> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends TextView> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public TextView get(int index) {
            return null;
        }

        @Override
        public TextView set(int index, TextView element) {
            return null;
        }

        @Override
        public void add(int index, TextView element) {

        }

        @Override
        public TextView remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<TextView> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<TextView> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<TextView> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    Button refreshBtn;

    OpenForecastMap openForecastMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        days.clear();

        days.add((TextView)findViewById(R.id.mondayText));
        days.add((TextView)findViewById(R.id.tuesdayText));
        days.add((TextView)findViewById(R.id.thursdayText));
        days.add((TextView)findViewById(R.id.fridayText));
        days.add((TextView)findViewById(R.id.saturdayText));
        days.add((TextView)findViewById(R.id.sundayText));

        refreshBtn = findViewById(R.id.btnRefresh);
    }

    private class GetForecast extends AsyncTask<String,Void,String> {
        ProgressDialog pd = new ProgressDialog(ForecastActivity.this);

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            pd.setTitle("Executing...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            Helper http = new Helper();
            stream = http.getHttpData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s == null){
                days.get(0).setText(String.format("Error, Connection could not be established"));
                return;
            }

            if(s.contains("Error: Not found city")){
                pd.dismiss();
                return;
            }

            Gson gson = new Gson();
            Type mType = new TypeToken<OpenForecastMap>(){}.getType();
            OpenForecastMap openForecastMap = gson.fromJson(s, mType);
            pd.dismiss();

            int counter = 0;

            for(TextView day:days){
                counter += 4;
                day.setText(openForecastMap.getOpenWeatherMap().get(counter).getCity());
                day.append(" " + openForecastMap.getOpenWeatherMap().get(counter).getName());
                day.append(" "+ Common.unixTimeStampToDate(openForecastMap.getOpenWeatherMap().get(counter).getDt()));
            }

        }
    }
}
