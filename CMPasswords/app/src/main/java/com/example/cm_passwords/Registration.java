package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm_passwords.db.DbHelper;
import com.example.cm_passwords.db.DbPassword;
import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void Registration(View view){
        TextView textoBienvenida = findViewById(R.id.welcome_text);

        DbHelper dbHelper = new DbHelper(Registration.this);
        DbPassword bdPassword = new DbPassword(Registration.this);
        TextInputEditText passwordInput = findViewById(R.id.password_input);
        TextInputEditText password2Input = findViewById(R.id.password2_input);
        TextView error = findViewById(R.id.regist_error_input);

        passwordInput.setBackgroundResource(R.drawable.custom_input);
        password2Input.setBackgroundResource(R.drawable.custom_input);
        error.setText("");

        String password = passwordInput.getText().toString();
        String password2 = password2Input.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db!=null && password.equals(password2))
        {
            Toast.makeText(Registration.this, "Welcome", Toast.LENGTH_LONG).show();
            bdPassword.insertMainPassword(password);
            Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
            startActivity(switchActivityIntent);
        }
        else if(password.length()<8)
        {
            passwordInput.setBackgroundResource(R.drawable.error_custom_input);
            error.setText("Enter a valid password");
        }
        else
        {
            Toast.makeText(Registration.this, "ERROR", Toast.LENGTH_LONG).show();
            passwordInput.setBackgroundResource(R.drawable.error_custom_input);
            password2Input.setBackgroundResource(R.drawable.error_custom_input);
            error.setText("Fields do not match");
        }
    }
}