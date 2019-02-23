package com.example.lennox.flexture;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Flexture extends AppCompatActivity {

    private ArrayList<String> fields;
    private GridView gridview;
    private GridAdapter adapter;
    public static Activity activity;
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
                Intent logout = new Intent(this, Login.class);
                mAuth.signOut();
                finish();
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
