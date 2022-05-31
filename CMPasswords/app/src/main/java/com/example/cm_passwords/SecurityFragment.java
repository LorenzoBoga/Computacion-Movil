package com.example.cm_passwords;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cm_passwords.db.DbPassword;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecurityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecurityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecurityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecurityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecurityFragment newInstance(String param1, String param2) {
        SecurityFragment fragment = new SecurityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        DbPassword dbPassword = new DbPassword(getContext());
        int[] countByStrength = dbPassword.measureStrength();

        count(countByStrength,view);
        return view;
    }
    private void count(int[] countByStrength, View view){
        TextView rating = view.findViewById(R.id.rating_value);
        Integer ratingValue = 0;
        Integer total = 0;

        TextView redCounter = view.findViewById(R.id.redCuantity);
        redCounter.setText(""+ countByStrength[0]);
        ratingValue += countByStrength[0];
        total += countByStrength[0];
        TextView orangeCounter = view.findViewById(R.id.orangeCuantity);
        orangeCounter.setText(""+ countByStrength[1]);
        ratingValue += countByStrength[1] * 2;
        total += countByStrength[1];
        TextView yellowCounter = view.findViewById(R.id.yellowCuantity);
        yellowCounter.setText(""+ countByStrength[2]);
        ratingValue += countByStrength[2] * 3;
        total += countByStrength[2];
        TextView lightgreenCounter = view.findViewById(R.id.lightgreenCuantity);
        lightgreenCounter.setText(""+ countByStrength[3]);
        ratingValue += countByStrength[3] * 4;
        total += countByStrength[3];
        TextView greenCounter = view.findViewById(R.id.greenCuantity);
        greenCounter.setText(""+ countByStrength[4]);
        ratingValue += countByStrength[4] * 5;
        total += countByStrength[4];
        if(total != 0)
            ratingValue = ratingValue/total;
        else
            ratingValue = 0;
        rating.setText(ratingValue.toString()+"/5");
    }

}