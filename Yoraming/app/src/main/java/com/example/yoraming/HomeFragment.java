package com.example.yoraming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

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

    public void onCreateButton(String text, int imageId) {
        Button mButton = new Button(getActivity());
        ViewGroup.LayoutParams pm = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mButton.setText(text);
        mButton.setBackgroundResource(imageId);
        mButton.setLayoutParams(pm);
        mButton.setOnClickListener(buttonlistener);
        FrameLayout mView = (FrameLayout)rootView.findViewById(R.id.home_container);
        mView.addView(mButton);
    }
}