package com.example.cm_passwords;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
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

        Button add = view.findViewById(R.id.add_button);
        EditText siteInput = view.findViewById(R.id.Site_input);
        EditText userInput = view.findViewById(R.id.User_input);
        EditText passwordInput = view.findViewById(R.id.Password_input);
        progressBar = view.findViewById(R.id.strengthBar);
        TextView strengthText = view.findViewById(R.id.strength_text);

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(passwordInput.getText().toString().equals("")){
                    progressBar.setProgress(0);
                    strengthText.setText("");
                }else{
                Password fakePsw = new Password();
                fakePsw.setPassword(passwordInput.getText().toString());
                switch(fakePsw.measureStrength()){
                    case 0:
                        progressBar.setProgress(20);
                        strengthText.setText("Very weak");
                        break;
                    case 1:
                        progressBar.setProgress(40);
                        strengthText.setText("Weak");
                        break;
                    case 2:
                        progressBar.setProgress(60);
                        strengthText.setText("Medium");
                        break;
                    case 3:
                        progressBar.setProgress(80);
                        strengthText.setText("Strong");
                        break;
                    case 4:
                        progressBar.setProgress(100);
                        strengthText.setText("Very strong");
                        break;
                }}
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

                DbPassword bdPassword = new DbPassword(getContext());
                bdPassword.insertPassword(site, user, password);

                siteInput.setText("");
                userInput.setText("");
                passwordInput.setText("");
            }
        });

        return view;
    }
}