package com.example.lennox.flexture;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private Button submitRegistration;
    private EditText et_fName, et_lName, et_regNumber, et_mainPass, et_sidePass, et_emailAddr, et_fonNum;
    private static String fName, lName, regNum, mainPwd, sidePwd, emailAddress, fonNumber;
    private Boolean studentSelected;
    private Spinner regSpinner;
    private TextInputLayout regInput;
    private FirebaseAuth studAuth;
    private FirebaseAuth lecAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        listeners();
    }

    private void listeners() {
        submitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        et_fName.addTextChangedListener(regWatcher);
        et_lName.addTextChangedListener(regWatcher);
        et_regNumber.addTextChangedListener(regWatcher);
        et_mainPass.addTextChangedListener(regWatcher);
        et_sidePass.addTextChangedListener(regWatcher);
        et_emailAddr.addTextChangedListener(regWatcher);
        et_fonNum.addTextChangedListener(regWatcher);

        //spinner listener
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regSpinner.setAdapter(adapter);
        regSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        regInput.setVisibility(View.VISIBLE);
                        studentSelected = true;
                        break;
                    case 1:
                        regInput.setVisibility(View.GONE);
                        studentSelected = false;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private TextWatcher regWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            fName = et_fName.getText().toString().trim();
            lName = et_lName.getText().toString().trim();
            regNum = et_regNumber.getText().toString().trim();
            mainPwd = et_mainPass.getText().toString().trim();
            sidePwd = et_sidePass.getText().toString().trim();
            emailAddress = et_emailAddr.getText().toString().trim();
            fonNumber = et_fonNum.getText().toString().trim();

            if (fName != null && lName != null && regNum != null && mainPwd != null &&
                    sidePwd != null && emailAddress != null && fonNumber != null)
                submitRegistration.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void register() {
        if (!validate()) {
            Toast.makeText(this, "Error, check your input", Toast.LENGTH_SHORT).show();
        }
        else {
            if (studentSelected) {
                studAuth.createUserWithEmailAndPassword(emailAddress, mainPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //sendEmailVerification();
                            sendStudentData(studAuth.getUid());
                            Toast.makeText(getBaseContext(), " Registration Successful!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Registration.this, Flexture.class).putExtra("ROLE", studentSelected));
                        }
                        else {
                            Toast.makeText(getBaseContext(), " Registration failed, try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if (!studentSelected) {
                lecAuth.createUserWithEmailAndPassword(emailAddress, mainPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //sendEmailVerification();
                            sendLecturerData(lecAuth.getUid());
                            Toast.makeText(getBaseContext(), " Registration Successful!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Registration.this, Flexture.class).putExtra("ROLE", studentSelected));
                        }
                        else {
                            Toast.makeText(getBaseContext(), " Registration failed, try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    private boolean validate() {
        boolean valid = true;

        if (fName.isEmpty() || fName.length() > 30 || checkName(fName)) {
            et_fName.setError("Invalid name");
            valid = false;
        }
        if (lName.isEmpty() || lName.length() > 30 || checkName(lName)) {
            et_lName.setError("Invalid name");
            valid = false;
        }
        if (studentSelected) {
            if (regNum.isEmpty() || checkReg(regNum)) {
                et_regNumber.setError("Invalid registration number");
                valid = false;
            }
        }
        if (mainPwd.isEmpty() || mainPwd.length() < 6) {
            et_mainPass.setError("Min characters 6");
            valid = false;
        }
        if (!sidePwd.equals(mainPwd)) {
            et_sidePass.setError("Password doesn't match");
            valid = false;
        }
        if (emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            et_emailAddr.setError("Invalid email address");
            valid = false;
        }
        if (fonNumber.isEmpty() || checkFon(fonNumber)) {
            et_fonNum.setError("Invalid number");
            valid = false;
        }
        return valid;
    }

    private boolean checkName(String name) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(name);
        return !(m.find() && m.group().equals(name));
    }

    private Boolean checkFon(String fonNumber) {
        return fonNumber.matches("[0][7]\\d{6}");
    }//incomplete till only ten digits allowed

    private Boolean checkReg(String regNum) {
        Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z]+[a-zA-Z]/d{4}/d{2}");
        Matcher m = p.matcher(regNum);
        return !(m.find() && m.group().equals(regNum));
    }// incomplete till the correct format is implemented

    private void init() {
        et_fName = (EditText) findViewById(R.id.first_name);
        et_lName = (EditText) findViewById(R.id.last_name);
        et_regNumber = (EditText) findViewById(R.id.registration_number);
        et_mainPass = (EditText) findViewById(R.id.password_1);
        et_sidePass = (EditText) findViewById(R.id.password_2);
        et_emailAddr = (EditText) findViewById(R.id.email);
        et_fonNum = (EditText) findViewById(R.id.phone_number);
        submitRegistration = (Button) findViewById(R.id.register_button);
        regSpinner = (Spinner) findViewById(R.id.reg_selector);
        regInput = findViewById(R.id.reg_input);
        studAuth = FirebaseAuth.getInstance();
        lecAuth = FirebaseAuth.getInstance();

    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = studAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Registration.this,"Verification email sent", Toast.LENGTH_SHORT).show();
                        if (studentSelected){
                            sendStudentData(studAuth.getUid());
                            studAuth.signOut();
                        }else{
                            sendLecturerData(lecAuth.getUid());
                            lecAuth.signOut();
                        }
                        finish();
                        startActivity(new Intent(Registration.this, Login.class));
                    }else{
                        Toast.makeText(Registration.this,"Verification email not sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendStudentData(String uid) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Students").child(uid);
        UserProfile userProfile = new UserProfile(fName, lName, regNum, emailAddress, fonNumber);
        myRef.setValue(userProfile);
    }

    private void sendLecturerData(String uid) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Lecturers").child(uid);
        UserProfile userProfile = new UserProfile(fName, lName, emailAddress, fonNumber);
        myRef.setValue(userProfile);
    }
}
