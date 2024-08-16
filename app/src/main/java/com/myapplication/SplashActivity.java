package com.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Animation rotate, alpha, translate, scale;
    ImageView imageview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);
        imageview = findViewById(R.id.image);
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        translate = AnimationUtils.loadAnimation(this,R.anim.translate);
        alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);
        scale = AnimationUtils.loadAnimation(this,R.anim.scale);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.startAnimation(rotate);
            }
        });

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageview.startAnimation(translate);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageview.startAnimation(alpha);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageview.startAnimation(scale);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
