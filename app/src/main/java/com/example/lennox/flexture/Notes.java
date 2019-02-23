package com.example.lennox.flexture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class Notes extends AppCompatActivity {
    private TextView tvCourseStudents, tvStudNum, tvStudentsSubmissions, tvFilessubmitted, tvSubInstr;
    private Spinner spInstrContent;
    private ListView notesPosted;
    private ImageButton btnUploadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        init();
        //check the role
        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            studentNotes();
        } else {
            lecturerNotes();
        }
    }

    private void lecturerNotes() {
        btnUploadFile.setVisibility(View.GONE);
        tvSubInstr.setVisibility(View.GONE);
        // not needed by lecturer
        // to add a notes adapter

    }

    private void studentNotes() {
        tvStudNum.setVisibility(View.GONE);
        tvStudentsSubmissions.setVisibility(View.GONE);
        tvFilessubmitted.setVisibility(View.GONE);
        tvCourseStudents.setVisibility(View.GONE);
        // not needed by student
        // to add a notes adapter class


    }

    private void init() {
        tvCourseStudents = findViewById(R.id.course_students);
        tvStudNum = findViewById(R.id.stud_num);
        tvStudentsSubmissions = findViewById(R.id.students_submissions);
        tvFilessubmitted = findViewById(R.id.files_submitted);
        tvSubInstr = findViewById(R.id.sub_instr);
        btnUploadFile = findViewById(R.id.upload_file);
        spInstrContent = findViewById(R.id.instr_content);
        notesPosted = findViewById(R.id.notes_list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
