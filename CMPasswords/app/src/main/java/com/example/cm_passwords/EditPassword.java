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

import java.util.ArrayList;

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

        TextView title = findViewById(R.id.title_add);
        title.setText(R.string.edit_password_title);
        TextView siteText = findViewById(R.id.site_title);
        siteText.setText(R.string.site_placeholder);
        TextView userText = findViewById(R.id.user_title);
        userText.setText(R.string.user_placeholder);
        TextView passwordText = findViewById(R.id.password_text);
        passwordText.setText(R.string.password_placeholder);

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

        siteInput.setBackgroundResource(R.drawable.custom_input);
        userInput.setBackgroundResource(R.drawable.custom_input);
        passwordInput.setBackgroundResource(R.drawable.custom_input);

        String[] parameters = {site, user, password};
        int missingParameter = emptyOrShort(parameters);
        if(missingParameter != -1){
            //errorText.setText("Complete all the parameters");
            switch (missingParameter){
                case 0:
                    siteInput.setBackgroundResource(R.drawable.error_custom_input);
                    break;
                case 1:
                    userInput.setBackgroundResource(R.drawable.error_custom_input);
                    break;
                case 2:
                    passwordInput.setBackgroundResource(R.drawable.error_custom_input);
                    break;
            }
        }else{
            bdPassword.editPasswords(site, user, password, id);

            Intent switchActivityIntent = new Intent(this, BottomNavigation.class);
            startActivity(switchActivityIntent);
        }
    }

    private int emptyOrShort(String[] strings){
        for(int i = 0; i < strings.length;i++){
            if(strings[i].equals("") || strings[i].length() < 4)
                return i;
        }
        return -1;
    }
}