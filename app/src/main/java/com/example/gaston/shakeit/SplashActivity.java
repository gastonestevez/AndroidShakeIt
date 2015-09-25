package com.example.gaston.shakeit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startAnimations();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }



    private void startAnimations(){
        ImageView iv = (ImageView) findViewById(R.id.i_splash);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        iv.clearAnimation();
        iv.startAnimation(anim);
    }
}
