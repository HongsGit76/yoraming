package com.example.yoraming.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoraming.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeChildFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeChildFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView num_majorR, num_majorS;
    private String majorR, majorS, univR, basicR;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeChildFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeChildFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeChildFragment1 newInstance(String param1, String param2) {
        HomeChildFragment1 fragment = new HomeChildFragment1();
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
        View v = inflater.inflate(R.layout.fragment_home_child1, container, false);

        num_majorR = (TextView)v.findViewById(R.id.num_majorR);
        num_majorS = (TextView)v.findViewById(R.id.num_majorS);
        //번들 받기. getArguments() 메소드로 받음.
        Bundle bundle = getArguments();
        if (bundle != null) {
            majorR = bundle.getString("majorR");
            majorS = bundle.getString("majorS");

            num_majorR.append(majorR);
            num_majorS.append(majorS);
        }
        return v;
    }
}