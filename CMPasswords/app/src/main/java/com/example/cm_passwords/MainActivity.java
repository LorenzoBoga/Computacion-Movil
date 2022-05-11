package com.example.cm_passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonEntrar = findViewById(R.id.enter_button);
    }

    public void IntentarIngresar(View view){
        TextView textoBienvenida = findViewById(R.id.welcome_text);
        textoBienvenida.setText("FUNCIONA");
    }
}