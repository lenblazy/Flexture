package com.example.lennox.flexture;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class Flexture extends AppCompatActivity {

    ArrayList<String> fields;
    GridView gridview;
    GridAdapter adapter;
    public static Activity activity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexture);
        init();
    }

    private void init() {
        activity = this;
        fields = new ArrayList<>();
        fields.add("ATTENDANCE");
        fields.add("SCHEDULER");
        fields.add("NOTES");
        fields.add("PROFILE");
        fields.add("CGPA CALCULATOR");
        gridview = (GridView) findViewById(R.id.grid);
        adapter = new GridAdapter(this, fields);
        gridview.setAdapter(adapter);
    }
}
