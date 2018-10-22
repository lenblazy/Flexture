package com.example.lennox.flexture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private Spinner roleSpinner;
    private TextView createAccount, resetPassword;
    private EditText et_userName, et_Password;
    private ArrayAdapter<CharSequence> adapter;
    private String userName, password;
    private Boolean studentSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        listeners();
    }

    private void listeners() {
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // use an intent to open the email so that a user may send a message
                Toast.makeText(getBaseContext(), "Check your email", Toast.LENGTH_SHORT).show();
            }
        });// not completed yet

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(Login.this, Registration.class);
                startActivity(regIntent);
            }
        });

        //spinner listener
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        studentSelected = true;
                        break;
                    case 1:
                        studentSelected = false;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //login listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainPageIntent = new Intent(Login.this, Flexture.class);

                if (studentSelected == true) {
                    boolean pass;
                    pass = validateStudent(userName, password);

                    if (pass == true) {
                        mainPageIntent.putExtra("ROLE", studentSelected);
                        startActivity(mainPageIntent);
                    } else {
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

                if (studentSelected == false) {
                    boolean pass;
                    pass = validateLecturer(userName, password);

                    if (pass == true) {
                        mainPageIntent.putExtra("ROLE", studentSelected);
                        startActivity(mainPageIntent);
                    } else {
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        et_userName.addTextChangedListener(loginTextWatcher);
        et_Password.addTextChangedListener(loginTextWatcher);
    }

    private boolean validateLecturer(String userName, String password) {
        return true;
    }//incomplete

    private boolean validateStudent(String userName, String password) {
        return true;
    }//incomplete

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String un = et_userName.getText().toString().trim();
            String pwd = et_Password.getText().toString().trim();

            userName = un;
            password = pwd;

            if (un != null && pwd != null)
                loginButton.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String un = et_userName.getText().toString().trim();
            String pwd = et_Password.getText().toString().trim();

            if (un != null && pwd != null)
                loginButton.setEnabled(true);
        }
    };
    // not completed yet

    private void init() {
        roleSpinner = (Spinner) findViewById(R.id.role_selector);
        loginButton = (Button) findViewById(R.id.login_button);
        resetPassword = (TextView) findViewById(R.id.reset_password);
        et_userName = (EditText) findViewById(R.id.user_id);
        et_Password = (EditText) findViewById(R.id.password);
        createAccount = (TextView) findViewById(R.id.create_account);
        adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);

    }
}
