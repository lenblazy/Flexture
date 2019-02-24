package com.example.lennox.flexture;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private Spinner roleSpinner;
    private TextView createAccount, resetPassword;
    private EditText et_userName, et_Password;
    private ArrayAdapter<CharSequence> adapter;
    private String email, password;
    private Boolean studentSelected = true;
    private FirebaseAuth studAuth, lecAuth;
    private ProgressBar progressBar;

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
                if(email.equals("")){
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }else{
                    if(studentSelected){
                        studAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                    resetPassword.setEnabled(false);
                                }else{
                                    Toast.makeText(Login.this, "Error occurred in sending reset email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });//from here
                    }
                    if(!studentSelected){
                        lecAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                    resetPassword.setEnabled(false);
                                }else{
                                    Toast.makeText(Login.this, "Error occurred in sending reset email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });//from here
                    }
                }

            }
        });// not completed yet

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(Login.this, Registration.class);
                finish();
                regIntent.putExtra("ROLE", studentSelected);
                regIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //prevents user from going back to previous screen
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

                if (studentSelected) {
                    progressBar.setVisibility(View.VISIBLE);
                    studAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                finish();
                                //checkEmailVerification();
                                startActivity(new Intent (Login.this, Flexture.class).putExtra("ROLE", studentSelected));
                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                if (!studentSelected) {
                    progressBar.setVisibility(View.VISIBLE);
                    studAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                finish();
                                //checkEmailVerification();
                                startActivity(new Intent (Login.this, Flexture.class).putExtra("ROLE", studentSelected));
                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        et_userName.addTextChangedListener(loginTextWatcher);
        et_Password.addTextChangedListener(loginTextWatcher);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String un = et_userName.getText().toString().trim();
            String pwd = et_Password.getText().toString().trim();

            email = un;
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
        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.GONE);

        //if user is currently logged in there is no need for validation
        if(studentSelected){
            studAuth = FirebaseAuth.getInstance();
            FirebaseUser student = studAuth.getCurrentUser();
            if(student != null){
                finish();
                startActivity(new Intent (this, Flexture.class).putExtra("ROLE", studentSelected));
            }
        }
        if(!studentSelected){
            lecAuth = FirebaseAuth.getInstance();
            FirebaseUser lecturer = lecAuth.getCurrentUser();
            if(lecturer != null){
                finish();
                startActivity(new Intent (this, Flexture.class).putExtra("ROLE", studentSelected));
            }
        }
    }

    private void checkEmailVerification(){
        if(studentSelected){
            FirebaseUser firebaseUser = studAuth.getCurrentUser();
            Boolean emailFlag = firebaseUser.isEmailVerified();

            if(emailFlag){
                finish();
                startActivity(new Intent (this, Flexture.class).putExtra("ROLE", studentSelected));
            }else{
                Toast.makeText(this, "verify your email", Toast.LENGTH_SHORT).show();
                studAuth.signOut();
            }
        }

        if(!studentSelected){
            FirebaseUser firebaseUser = lecAuth.getCurrentUser();
            Boolean emailFlag = firebaseUser.isEmailVerified();

            if(emailFlag){
                finish();
                startActivity(new Intent (this, Flexture.class).putExtra("ROLE", studentSelected));
            }else{
                Toast.makeText(this, "verify your email", Toast.LENGTH_SHORT).show();
                lecAuth.signOut();
            }
        }
    }

}
