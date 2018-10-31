package com.example.lennox.flexture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    private TextView tvSelCourse, tvSelyear, tvSelSem;
    private Spinner spCoursesTaught, spYearSelector, spSemSelector;
    private Button print;
    private TableLayout studTable;
    private TableLayout lecTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        init();
    }

    private void init() {
        tvSelCourse = findViewById(R.id.sel_course);
        tvSelyear = findViewById(R.id.sel_year);
        tvSelSem = findViewById(R.id.sel_sem);
        spCoursesTaught= findViewById(R.id.courses_taught);
        spYearSelector= findViewById(R.id.year_selector);
        spSemSelector = findViewById(R.id.sem_selector);
        print = findViewById(R.id.print_results);
        studTable= findViewById(R.id.stud_results_table);
        lecTable= findViewById(R.id.lec_results_table);

        Boolean role = getIntent().getBooleanExtra("ROLE", true);
        //true value for students and false value for lecturers
        if (role) {
            studentResults();
        } else {
            lecturerResults();
        }
    }

    private void lecturerResults() {
        tvSelCourse.setVisibility(View.VISIBLE);
        spCoursesTaught.setVisibility(View.VISIBLE);
        lecTable.setVisibility(View.VISIBLE);
        print.setText("Submit results");
    }

    private void studentResults() {
        //the gui is correct
    }
}
