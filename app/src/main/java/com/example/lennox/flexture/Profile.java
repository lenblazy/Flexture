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
<<<<<<< HEAD
<<<<<<< HEAD
    TextView tvFName, tvLName, tvRegNum, tvFonNum, tvEmail;
    ImageView profilePic;
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        listeners();
<<<<<<< HEAD
<<<<<<< HEAD

        //check the role
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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(lecAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(Profile.this,"It worked", Toast.LENGTH_SHORT).show();
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                tvFName.setText(userProfile.getFirstName());
                tvLName.setText(userProfile.getLastName());
                tvFonNum.setText(userProfile.getPhoneNumber());
                tvEmail.setText(userProfile.getEmailAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,"Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void studentProfile() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Students").child(firebaseAuth.getUid());
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
                Toast.makeText(Profile.this,"Failed", Toast.LENGTH_SHORT).show();
            }
        });
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
=======
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it
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
