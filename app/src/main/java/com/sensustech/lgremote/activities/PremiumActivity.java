package com.sensustech.lgremote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.sensustech.lgremote.R;

public class PremiumActivity extends AppCompatActivity {
    private Button premium_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        final Animation anim = new ScaleAnimation(
                1f, 1.075f, // Start and end values for the X axis scaling
                1f, 1.075f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(900);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        premium_button = findViewById(R.id.button7);
        premium_button.setAnimation(anim);
    }

    public void backClick(View view) {
        finish();
    }
}