package com.example.lennox.flexture;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Flexture extends AppCompatActivity {

<<<<<<< HEAD
    private ArrayList<String> fields;
    public Activity activity;
=======
    ArrayList<String> fields;
    GridView gridview;
    GridAdapter adapter;
    public static Activity activity;
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();

        //check the role
        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            studentActivities();
        } else {
            lecturerActivities();
        }

        GridView gridview = findViewById(R.id.grid);
        GridAdapter adapter = new GridAdapter(this, fields, role);
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
                startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
