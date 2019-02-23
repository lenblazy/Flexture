package com.example.lennox.flexture;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
<<<<<<< HEAD
=======

import com.google.firebase.auth.FirebaseAuth;
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it

import java.util.ArrayList;

public class Flexture extends AppCompatActivity {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private ArrayList<String> fields;
    public Activity activity;
=======
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
    ArrayList<String> fields;
    GridView gridview;
    GridAdapter adapter;
    public static Activity activity;
<<<<<<< HEAD
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
    private FirebaseAuth mAuth;
=======
>>>>>>> parent of 95dc3c3... Able to send data successfully to firebase

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
<<<<<<< HEAD
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, Login.class));
=======
                //logout user from system, not yet complete
                Intent logout = new Intent(this, Login.class);
                finish();
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //prevents user from going back to previous screen
                startActivity(logout);
>>>>>>> parent of 95dc3c3... Able to send data successfully to firebase
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
