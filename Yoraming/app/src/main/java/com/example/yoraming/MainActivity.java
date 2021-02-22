package com.example.yoraming;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManger;
    private Fragment currentFragment = null;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManger = getSupportFragmentManager();

        transaction = fragmentManger.beginTransaction();
        transaction.replace(R.id.fragment_container, new HomeFragment()).commitAllowingStateLoss();
        findViewById(R.id.tab_home).setSelected(true);
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManger.beginTransaction();
                switch (v.getId()) {
                    case R.id.tab_home:
                        currentFragment = new HomeFragment();
                        findViewById(R.id.tab_home).setSelected(true);
                        findViewById(R.id.tab_detail).setSelected(false);
                        findViewById(R.id.tab_currentSchedule).setSelected(false);
                        findViewById(R.id.tab_afterSchedule).setSelected(false);
                        break;
                    case R.id.tab_detail:
                        currentFragment = new DetailFragment();
                        findViewById(R.id.tab_home).setSelected(false);
                        findViewById(R.id.tab_detail).setSelected(true);
                        findViewById(R.id.tab_currentSchedule).setSelected(false);
                        findViewById(R.id.tab_afterSchedule).setSelected(false);
                        break;
                    case R.id.tab_currentSchedule:
                        currentFragment = new currentScheduleFragment();
                        findViewById(R.id.tab_home).setSelected(false);
                        findViewById(R.id.tab_detail).setSelected(false);
                        findViewById(R.id.tab_currentSchedule).setSelected(true);
                        findViewById(R.id.tab_afterSchedule).setSelected(false);
                        break;
                    case R.id.tab_afterSchedule:
                        currentFragment = new afterScheduleFragment();
                        findViewById(R.id.tab_home).setSelected(false);
                        findViewById(R.id.tab_detail).setSelected(false);
                        findViewById(R.id.tab_currentSchedule).setSelected(false);
                        findViewById(R.id.tab_afterSchedule).setSelected(true);
                        break;
                    default:
                        return;
                }
                transaction.replace(R.id.fragment_container, currentFragment).commitAllowingStateLoss();
            }
        };

        findViewById(R.id.tab_home).setOnClickListener(buttonListener);
        findViewById(R.id.tab_detail).setOnClickListener(buttonListener);
        findViewById(R.id.tab_currentSchedule).setOnClickListener(buttonListener);
        findViewById(R.id.tab_afterSchedule).setOnClickListener(buttonListener);
    }
}