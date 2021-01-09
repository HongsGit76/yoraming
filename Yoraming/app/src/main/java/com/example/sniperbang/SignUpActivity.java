package com.example.sniperbang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

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