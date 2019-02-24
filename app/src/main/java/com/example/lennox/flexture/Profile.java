package com.example.lennox.flexture;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    Button btnSave;
    Spinner spFaculty, spDept, spLevel, spProg, spYear, spSemester;
    TextView fName ,lName, regNum, fonNum, em;
    private static final String TAG = Profile.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        listeners();

        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            studentProfile();
        } else {
            lecturerProfile();
        }
    }

    private void lecturerProfile() {
        FirebaseAuth lecAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Lecturers").child(lecAuth.getUid());
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile up = dataSnapshot.getValue(UserProfile.class);
                fName.setText(up.getFirstName());
                lName.setText(up.getLastName());
                fonNum.setText(up.getPhoneNumber());
                em.setText(up.getEmailAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(listener);
    }

    private void studentProfile() {
        FirebaseAuth studAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Students").child(studAuth.getUid());
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile up = dataSnapshot.getValue(UserProfile.class);
                fName.setText(up.getFirstName());
                lName.setText(up.getLastName());
                regNum.setText(up.getRegNumber());
                fonNum.setText(up.getPhoneNumber());
                em.setText(up.getEmailAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(listener);
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
        ListView lvCourses = (ListView) findViewById(R.id.courses);

        //textviews
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        regNum = findViewById(R.id.idNumber);
        fonNum = findViewById(R.id.fonNum);
        em = findViewById(R.id.em);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
