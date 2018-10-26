package com.example.lennox.flexture;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Scheduler extends AppCompatActivity {

    TableLayout table_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        init();
    }

    private void init() {
        table_classes = findViewById(R.id.title_classes);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check the role
        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            addStudentData(table_classes);
        } else {
            addLecturerData(table_classes);
        }
    }

    private void addLecturerData(TableLayout table) {
        List<Classes> classes = DBHandler.getClasses(this);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        int classNum = 1;

        for (final Classes c : classes) {
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.row_adapter, null);

            ((TextView) row.findViewById(R.id.course_code)).setText(c.getCode());
            ((TextView) row.findViewById(R.id.course_title)).setText(c.getTitle());
            ((TextView) row.findViewById(R.id.class_venue)).setText(c.getVenue());
            ((TextView) row.findViewById(R.id.class_date)).setText(c.getDate());
            ((TextView) row.findViewById(R.id.class_time)).setText(c.getTime());

            // handle update button
            ImageButton btnUpdate = (ImageButton) row.findViewById(R.id.btn_update);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent = new Intent( ListClassesActivity.this, UpdateClassActivity.class);
                    // intent.putExtra("classid", c.getClassId());
                    // startActivity(intent);
                }
            });

            table.addView(row);

            TableRow line = new TableRow(this);
            View v = new View(this);
            v.setBackgroundColor(Color.RED);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 3);
            lp.span = 4;
            v.setLayoutParams(lp);

            line.addView(v);

            table.addView(line);
            classNum++;
        }
    }

    private void addStudentData(TableLayout table) {
        List<Classes> classes = DBHandler.getClasses(this);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        int classNum = 1;

        for (final Classes c : classes) {
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.row_adapter, null);

            ((TextView) row.findViewById(R.id.course_code)).setText(c.getCode());
            ((TextView) row.findViewById(R.id.course_title)).setText(c.getTitle());
            ((TextView) row.findViewById(R.id.class_venue)).setText(c.getVenue());
            ((TextView) row.findViewById(R.id.class_date)).setText(c.getDate());
            ((TextView) row.findViewById(R.id.class_time)).setText(c.getTime());

            // handle update button
            ImageButton btnUpdate = (ImageButton) row.findViewById(R.id.btn_update);
            btnUpdate.setVisibility(View.GONE);
            table.addView(row);

            TableRow line = new TableRow(this);
            View v = new View(this);
            v.setBackgroundColor(Color.RED);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 3);
            lp.span = 4;
            v.setLayoutParams(lp);

            line.addView(v);

            table.addView(line);
            classNum++;
        }

    }

}
