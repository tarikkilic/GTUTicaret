package com.grup15.gtuticaret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by Serkan Sorman on 25.04.2018.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        final ImageView logo = findViewById(R.id.imageView);


        //Creates animations
        final Animation bubble = AnimationUtils.loadAnimation(this, R.anim.bubble);
        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        final AnimationSet as = new AnimationSet(true);

        bubble.setDuration(2500);
        as.addAnimation(rotate);
        as.addAnimation(bubble);
        logo.startAnimation(as);


        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(shake);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {

                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                finish();


            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}