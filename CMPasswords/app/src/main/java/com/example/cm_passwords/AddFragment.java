package com.example.cm_passwords;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cm_passwords.db.DbPassword;
import com.example.cm_passwords.entities.Password;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    private Context globalContext = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalContext = this.getContext();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_add, container, false);

        Button add = view.findViewById(R.id.edit_button);
        add.setText(R.string.add_button_text);
        EditText siteInput = view.findViewById(R.id.Site_input);
        siteInput.setHint(R.string.site_placeholder);
        EditText userInput = view.findViewById(R.id.User_input);
        userInput.setHint(R.string.user_placeholder);
        EditText passwordInput = view.findViewById(R.id.Password_input);
        passwordInput.setHint(R.string.password_placeholder);
        
        progressBar = view.findViewById(R.id.strengthBar);
        TextView strengthText = view.findViewById(R.id.strength_text);
        TextView errorText = view.findViewById(R.id.error_input);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String site = siteInput.getText().toString();
                String user = userInput.getText().toString();
                String password = passwordInput.getText().toString();

                siteInput.setBackgroundResource(R.drawable.custom_input);
                userInput.setBackgroundResource(R.drawable.custom_input);
                passwordInput.setBackgroundResource(R.drawable.custom_input);

                DbPassword bdPassword = new DbPassword(getContext());
                if (site.length()>0 && user.length()>0 && password.length()>0) {
                    bdPassword.insertPassword(site, user, password);
                    siteInput.setText("");
                    userInput.setText("");
                    passwordInput.setText("");
                }
                if(site.length()==0){
                    errorText.setText("Complete all the parameters");
                    siteInput.setBackgroundResource(R.drawable.error_custom_input);
                }
                if (user.length()==0){
                    errorText.setText("Complete all the parameters");
                    userInput.setBackgroundResource(R.drawable.error_custom_input);
                }
                if(password.length()==0){
                    errorText.setText("Complete all the parameters");
                    passwordInput.setBackgroundResource(R.drawable.error_custom_input);
                }
            }
        });

        Button hidePassword = view.findViewById(R.id.show_button);
        hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidePassword.getText().toString().equalsIgnoreCase("Show")){
                    passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassword.setText("Hide");
                } else{
                    passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassword.setText("Show");
                }
            }
        });
        return view;
    }
}