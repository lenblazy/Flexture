package com.example.lennox.flexture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ClassSession extends AppCompatActivity {

    private Button start, send;
    private TextView timeRem, totalStud, onlineStud;
    private ListView questions;
    private EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_session);
        init();
    }

    private void init() {
        start = findViewById(R.id.start);
        send = findViewById(R.id.send_button);
        timeRem = findViewById(R.id.time_rem);
        totalStud = findViewById(R.id.stud_registered);
        onlineStud = findViewById(R.id.stud_online);
        questions = findViewById(R.id.msgs);
        query = findViewById(R.id.write_msg);

        //check the role
        Boolean role = getIntent().getBooleanExtra("ROLE", true);

        //true value for students and false value for lecturers
        if (role) {
            start.setText(R.string.join);
            studentActivities();
        } else {
            start.setText(R.string.start);
            lecturerActivities();
        }

    }

    private void lecturerActivities() {
        send.setVisibility(View.GONE);
        query.setVisibility(View.GONE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(start.getText().toString().equals("Start")){
                    //to start the session
                    start.setText(R.string.stop);
                }else{
                    //to stop the session
                    start.setText(R.string.start);
                }
            }
        });

        //update timeRem with time
        //update totalStud with db
        //update onlineStud with conn status
    }

    private void studentActivities() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setEnabled(true);
                //get the time remaining for the class session and display it on textview
                //get current time from calender or firebase db and compare it to schedule
                //to join the session
                if(start.getText().toString().equals("Join Class")){
                    //to start the session
                    start.setText(R.string.leave);
                }else{
                    //to stop the session
                    start.setText(R.string.join);
                }
            }
        });

        query.addTextChangedListener(queryTextWatcher);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send the msg to lecturer
            }
        });

    }

    private TextWatcher queryTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String quiz = query.getText().toString().trim();

            if (quiz != null)
                send.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_session_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_attendance:
                //open attendance page
                return true;
            case R.id.refresh_session:
                //reload the page
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
