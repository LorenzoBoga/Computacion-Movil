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
        TextInputEditText password = findViewById(R.id.password_input);
        TextInputEditText password2 = findViewById(R.id.password2_input);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db!=null && password.equals(password2))
        {
            Toast.makeText(Registration.this, "Welcome", Toast.LENGTH_LONG).show();
            bdPassword.insertMainPassword(password.getText().toString());
            Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
            startActivity(switchActivityIntent);
        }
        else
        {
            Toast.makeText(Registration.this, "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}