package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import com.example.cm_passwords.db.DbHelper;
import com.example.cm_passwords.db.DbPassword;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button createDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbPassword bdPassword = new DbPassword(MainActivity.this);
        createDB = findViewById(R.id.enter_button);

        TextView textoBienvenida = findViewById(R.id.welcome_text);
        textoBienvenida.setText(R.string.welcome_title);
        TextView mainTitle = findViewById(R.id.welcome_text);
        mainTitle.setText(R.string.main_title);
        createDB.setText(R.string.login_button);
        EditText mainPasswordInput = findViewById(R.id.password_input);
        mainPasswordInput.setHint(R.string.main_password_placehorder);

        try{
            bdPassword.isMainPassword("");
        }
        catch(Exception e)
        {
            Intent switchActivityIntent = new Intent(this, Registration.class);
            startActivity(switchActivityIntent);
        }
    }

    public void IntentarIngresar(View view){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        DbPassword bdPassword = new DbPassword(MainActivity.this);
        TextInputEditText password = findViewById(R.id.password_input);
        password.setBackgroundResource(R.drawable.custom_input);


        if (bdPassword.isMainPassword(password.getText().toString()))
        {
            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
            Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
            startActivity(switchActivityIntent);
        }
        else
        {
            password.setBackgroundResource(R.drawable.error_custom_input);
            Toast.makeText(MainActivity.this, "CONTRASEÑA INCORRECTA", Toast.LENGTH_LONG).show();
        }
    }

}