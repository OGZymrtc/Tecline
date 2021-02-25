package com.ogzymrtc.tecline.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ogzymrtc.tecline.R;

public class AnimationActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    Animation middle_animation;
    ImageView animation_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);

        middle_animation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        animation_logo = findViewById(R.id.animationLogo);
        animation_logo.setAnimation(middle_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AnimationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
}
}