package com.example.lennox.flexture;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private Button submitRegistration;
    private EditText et_fName, et_lName, et_regNumber, et_mainPass, et_sidePass, et_emailAddr, et_fonNum;
    public String fName, lName, regNum, mainPwd, sidePwd, emailAddress, fonNumber;
    private Boolean studentSelected;
    private Spinner regSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private TextInputLayout regInput;

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
        adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
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
        Intent flexture = new Intent(Registration.this, Flexture.class);

        if (!validate()) {
            Toast.makeText(this, "Registration has failed", Toast.LENGTH_SHORT).show();
        }
        else {
            if (studentSelected == true) {
                boolean regSuccess = true;
                //save the reg details in firebase
                if (regSuccess == true) {
                    finish();
                    Toast.makeText(getBaseContext(), " Successful registration", Toast.LENGTH_SHORT).show();
                    flexture.putExtra("ROLE", studentSelected);
                    flexture.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //prevents user from going back to previous screen
                    startActivity(flexture);
                } else {
                    Toast.makeText(getBaseContext(), " Registration not successful, try again!", Toast.LENGTH_SHORT).show();
                }
            }
            if (studentSelected == false) {
                boolean regSuccess = true;
                //save the reg details in firebase
                if (regSuccess == true) {
                    finish();
                    Toast.makeText(getBaseContext(), " Successful registration", Toast.LENGTH_SHORT).show();
                    flexture.putExtra("ROLE", studentSelected);
                    flexture.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //prevents user from going back to previous screen
                    startActivity(flexture);
                } else {
                    Toast.makeText(getBaseContext(), " Registration not successful, try again!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }// incomplete till you connect to firebase

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
        if (studentSelected == true) {
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
        if (m.find() && m.group().equals(name)) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkFon(String fonNumber) {
        if (fonNumber.matches("[0][7]\\d{6}")) {
            return true;
        }
        return false;
    }//incomplete till only ten digits allowed

    private Boolean checkReg(String regNum) {
        Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z]+[a-zA-Z]/d{4}/d{2}");
        Matcher m = p.matcher(regNum);
        if (m.find() && m.group().equals(regNum)) {
            return true;
        } else {
            return false;
        }
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
        Boolean studentSelected = getIntent().getBooleanExtra("ROLE", true);
        if (studentSelected) {
            //set the selected text to be student
        } else {
            //set default value to be lecturer
        }
    }
}
