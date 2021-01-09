package com.example.sniperbang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2500;

    //Variables
    Animation introimage;
    ImageView image;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        //Animations
        introimage = AnimationUtils.loadAnimation(this, R.anim.introimage_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        slogan = findViewById(R.id.textView);

        image.setAnimation(introimage);
        slogan.setAnimation(introimage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}