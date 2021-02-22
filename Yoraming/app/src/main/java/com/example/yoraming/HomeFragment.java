package com.example.yoraming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    View.OnClickListener buttonlistener;
    View rootView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        buttonlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }
}