package com.example.lennox.flexture;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    Button btnSave;
    Spinner spFaculty, spDept, spLevel, spProg, spYear, spSemester;
    ListView lvCourses;
    TextView tvFName, tvLName, tvRegNum, tvFonNum, tvEmail;
    ImageView profilePic;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        listeners();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(Profile.this,"It worked", Toast.LENGTH_SHORT).show();
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                tvFName.setText(userProfile.getFirstName());
                tvLName.setText(userProfile.getLastName());
                tvRegNum.setText(userProfile.getRegNumber());
                tvFonNum.setText(userProfile.getPhoneNumber());
                tvEmail.setText(userProfile.getEmailAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        btnSave = findViewById(R.id.save_details);
        spDept = findViewById(R.id.sp_dept);
        spFaculty = findViewById(R.id.sp_faculty);
        spLevel = findViewById(R.id.sp_level);
        spProg = findViewById(R.id.sp_prog);
        spYear = findViewById(R.id.sp_year);
        spSemester = findViewById(R.id.sp_semester);
        lvCourses = (ListView) findViewById(R.id.courses);
        tvFName = findViewById(R.id.fName);
        tvLName = findViewById(R.id.lName);
        tvRegNum = findViewById(R.id.idNumber);
        tvFonNum = findViewById(R.id.fonNum);
        tvEmail = findViewById(R.id.em);
        profilePic = findViewById(R.id.display_photo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
