package com.example.yoraming.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoraming.R;

public class SignUpActivity extends AppCompatActivity {
    private Spinner spn_major_1, spn_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spn_major_1 = (Spinner)findViewById(R.id.spn_major_1);
        spn_major_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spn_current = (Spinner)findViewById(R.id.spn_current);
        spn_current.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
}