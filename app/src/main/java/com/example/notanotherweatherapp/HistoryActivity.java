package com.example.notanotherweatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notanotherweatherapp.Database.DBManager;

import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import org.xml.sax.helpers.AttributesImpl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HistoryActivity extends AppCompatActivity {

    LinearLayout historyLayout;
    Button btnRefresh;
    Button btnBack;

    DBManager db = new DBManager(this);

    private final int HISTORY_ITEM_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnBack = findViewById(R.id.btnBack);
        btnRefresh = findViewById(R.id.btnRefresh);
        historyLayout = findViewById(R.id.historyLayout);

        btnRefresh.setOnClickListener(onClick);

        refresh();
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRefresh:
                    refresh();
                    break;
                case R.id.btnBack:

                    break;

            }
        }
    };

    private void refresh() {
        String[] historyStringArray = getDBData(HISTORY_ITEM_COUNT);

        historyLayout.removeAllViews();
        historyLayout.setWeightSum(HISTORY_ITEM_COUNT);

        for(String item : historyStringArray){
            TextView tv = new TextView(this);
            tv.setText(item);
            historyLayout.addView(tv);
        }
    }

    private String[] getDBData(int historyCount){
            return db.fetchWithLimiter(historyCount).split("\n");
        }
    }
