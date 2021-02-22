package com.example.yoraming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManger;
    private Fragment currentFragment = null;
    private FragmentTransaction transaction;
    private ImageView tab_home, tab_detail, tab_mypage, tab_yoraming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManger = getSupportFragmentManager();

        transaction = fragmentManger.beginTransaction();
        transaction.replace(R.id.fragment_container, new HomeFragment()).commitAllowingStateLoss();

        tab_home = (ImageView) findViewById(R.id.tab_home);
        tab_home.setSelected(true);
        tab_detail = (ImageView) findViewById(R.id.tab_detail);
        tab_yoraming = (ImageView) findViewById(R.id.tab_yoraming);
        tab_mypage = (ImageView) findViewById(R.id.tab_mypage);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManger.beginTransaction();
                switch (v.getId()) {
                    case R.id.tab_home:
                        tab_home.setSelected(true);
                        tab_detail.setSelected(false);
                        tab_yoraming.setSelected(false);
                        tab_mypage.setSelected(false);
                        currentFragment = new HomeFragment();
                        break;
                    case R.id.tab_detail:
                        tab_home.setSelected(false);
                        tab_detail.setSelected(true);
                        tab_yoraming.setSelected(false);
                        tab_mypage.setSelected(false);
                        currentFragment = new DetailFragment();
                        break;
                    case R.id.tab_yoraming:
                        tab_home.setSelected(false);
                        tab_detail.setSelected(false);
                        tab_yoraming.setSelected(true);
                        tab_mypage.setSelected(false);
                        currentFragment = new yoramingFragment();
                        break;
                    case R.id.tab_mypage:
                        tab_home.setSelected(false);
                        tab_detail.setSelected(false);
                        tab_yoraming.setSelected(false);
                        tab_mypage.setSelected(true);
                        currentFragment = new myPageFragment();
                        break;
                    default:
                        return;
                }
                transaction.replace(R.id.fragment_container, currentFragment).commitAllowingStateLoss();
            }
        };

        findViewById(R.id.tab_home).setOnClickListener(buttonListener);
        findViewById(R.id.tab_detail).setOnClickListener(buttonListener);
        findViewById(R.id.tab_yoraming).setOnClickListener(buttonListener);
        findViewById(R.id.tab_mypage).setOnClickListener(buttonListener);
    }

}