package com.example.lennox.flexture;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    DBHandler dbHandler;
    Button btnSave;
    Spinner spFaculty, spDept, spLevel, spProg, spYear, spSemester;
    ListView lvCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        listeners();
    }

    private void listeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to save details to db
               // saveDetails();
            }
        });
    }


    private void init() {
        dbHandler = new DBHandler(this);
        btnSave = findViewById(R.id.save_details);
        spDept = findViewById(R.id.sp_dept);
        spFaculty = findViewById(R.id.sp_faculty);
        spLevel = findViewById(R.id.sp_level);
        spProg = findViewById(R.id.sp_prog);
        spYear = findViewById(R.id.sp_year);
        spSemester = findViewById(R.id.sp_semester);
        lvCourses = (ListView) findViewById(R.id.courses);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
