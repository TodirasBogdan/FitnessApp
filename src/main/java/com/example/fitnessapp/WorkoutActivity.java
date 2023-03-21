package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class WorkoutActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ImageView icanchor;
    Animation roundingAlone;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        icanchor = findViewById(R.id.icanchor);
        timer = findViewById(R.id.timer);


        roundingAlone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        btnStop.setAlpha(0);

        Typeface MMmedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        btnStart.setTypeface(MMmedium);
        btnStop.setTypeface(MMmedium);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.startAnimation(roundingAlone);
                btnStart.animate().alpha(0).setDuration(1000).start();
                btnStop.animate().alpha(1).translationY(-80).setDuration(1000).start();
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.clearAnimation();
                timer.stop();
                btnStop.animate().alpha(0).setDuration(1000).start();
                btnStart.animate().alpha(1).translationY(-80).setDuration(1000).start();
            }
        });
    }
}