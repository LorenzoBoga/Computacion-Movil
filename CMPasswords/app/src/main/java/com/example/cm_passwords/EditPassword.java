package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cm_passwords.db.DbHelper;
import com.example.cm_passwords.db.DbPassword;

public class EditPassword extends AppCompatActivity {

    Bundle parameters;

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
        EditText passwordInput = findViewById(R.id.Password_input);

        siteInput.setText(site);
        userInput.setText(user);
        passwordInput.setText(password);
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

    public void deletePassword(View view){
        DbHelper dbHelper = new DbHelper(EditPassword.this);
        DbPassword bdPassword = new DbPassword(EditPassword.this);

        Integer id = parameters.getInt("id");

        bdPassword.deletePassword(id);

        Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
        startActivity(switchActivityIntent);
    }
}