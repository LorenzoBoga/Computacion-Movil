package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cm_passwords.db.DbHelper;
import com.example.cm_passwords.db.DbPassword;
import com.example.cm_passwords.entities.Password;

public class EditPassword extends AppCompatActivity {

    Bundle parameters;
    EditText passwordInput;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        parameters = getIntent().getExtras();
        String site = parameters.getString("site");
        String user = parameters.getString("user");
        String password = parameters.getString("password");

        EditText siteInput = findViewById(R.id.Site_input);
        EditText userInput = findViewById(R.id.User_input);
        passwordInput = findViewById(R.id.Password_input);
        progressBar = findViewById(R.id.strengthBar);
        TextView strengthText = findViewById(R.id.strength_text);

        siteInput.setText(site);
        userInput.setText(user);
        passwordInput.setText(password);

        Password.changeStrenghtBar(passwordInput,progressBar,strengthText);

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Password.changeStrenghtBar(passwordInput,progressBar,strengthText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void editPassword(View view){
        DbHelper dbHelper = new DbHelper(EditPassword.this);
        DbPassword bdPassword = new DbPassword(EditPassword.this);

        EditText siteInput = findViewById(R.id.Site_input);
        EditText userInput = findViewById(R.id.User_input);
        EditText passwordInput = findViewById(R.id.Password_input);

        String site = siteInput.getText().toString();
        String user = userInput.getText().toString();
        String password = passwordInput.getText().toString();
        Integer id = parameters.getInt("id");

        bdPassword.editPasswords(site, user, password, id);

        Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
        startActivity(switchActivityIntent);
    }
}