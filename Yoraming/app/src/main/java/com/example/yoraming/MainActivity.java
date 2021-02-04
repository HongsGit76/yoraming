package com.example.yoraming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MainScreenUI currentUI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_button_1:
                        currentUI = new MainMenu1ScreenUI();
                        currentUI.show();
                        break;
                    case R.id.main_button_2:
                        currentUI = new MainMenu2ScreenUI();
                        currentUI.show();
                        break;
                    case R.id.main_button_3:
                        currentUI = new MainMenu3ScreenUI();
                        currentUI.show();
                        break;
                    default:
                        return;
                }
            }
        };

        findViewById(R.id.main_button_1).setOnClickListener(buttonListener);
        findViewById(R.id.main_button_2).setOnClickListener(buttonListener);
        findViewById(R.id.main_button_3).setOnClickListener(buttonListener);
    }

}