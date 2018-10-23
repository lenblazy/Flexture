package com.example.lennox.flexture;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

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

        //check the role
        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            studentActivities();
        } else {
            lecturerActivities();
        }

        gridview = findViewById(R.id.grid);
        adapter = new GridAdapter(this, fields, role);
        gridview.setAdapter(adapter);
    }

    private void lecturerActivities() {
        fields.add("CLASS SESSION");
        fields.add("SCHEDULER");
        fields.add("NOTES");
        fields.add("PROFILE");
        fields.add("RESULTS");
    }

    private void studentActivities() {
        fields.add("CLASS SESSION");
        fields.add("TIME TABLE");
        fields.add("NOTES");
        fields.add("PROFILE");
        fields.add("RESULTS");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                //open settings page
                return true;
            case R.id.about:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                return true;
            case R.id.logout:
                //logout user
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
