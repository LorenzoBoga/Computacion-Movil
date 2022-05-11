package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import com.example.cm_passwords.db.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button createDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB = findViewById(R.id.enter_button);


    public void IntentarIngresar(View view){
        TextView textoBienvenida = findViewById(R.id.welcome_text);
        Intent switchActivityIntent = new Intent(this, ShowPasswords.class);
        startActivity(switchActivityIntent);

        createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db!=null)
                {
                    Toast.makeText(MainActivity.this, "DATA BASE CREATED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
              TextView textoBienvenida = findViewById(R.id.welcome_text);
              textoBienvenida.setText("FUNCIONA");
            }
        });
    }
}