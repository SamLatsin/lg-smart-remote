package com.sensustech.lgremote.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.LaunchSession;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.SingletonTV;
import com.sensustech.lgremote.utils.OnSwipeTouchListener;
import com.connectsdk.service.capability.KeyControl.KeyCode;

public class SwipeControlActivity extends AppCompatActivity {
    TextView tv_swipe_check;
    ConnectableDevice mTV;
    boolean  mute_state;
    public LaunchSession inputPickerSession;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_control);
        ConstraintLayout constraint = findViewById(R.id.constraint);
        tv_swipe_check = findViewById(R.id.textGesture);
        final Animation anim = new ScaleAnimation(
                1f, 1.075f, // Start and end values for the X axis scaling
                1f, 1.075f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(100);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(1);
        mute_state = false;
        SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
        mTV = tv.getTV();
        constraint.setOnTouchListener(new OnSwipeTouchListener() {
            public void onSwipeTop() {
                tv_swipe_check.setText("Swipe Up");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getVolumeControl().volumeUp(null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onSwipeRight() {
                tv_swipe_check.setText("Swipe Right");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getTVControl().channelUp(null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onSwipeLeft() {
                tv_swipe_check.setText("Swipe Left");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getTVControl().channelDown(null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onSwipeBottom() {
                tv_swipe_check.setText("Swipe Down");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getVolumeControl().volumeDown(null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onSingleTap() {
                tv_swipe_check.setText("Tap");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onDoubleTap() {
                tv_swipe_check.setText("Double Tap");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    if (mute_state == false){
                        mute_state = true;
                        mTV.getVolumeControl().setMute(true, null);
                    }
                    else {
                        mute_state = false;
                        mTV.getVolumeControl().setMute(false, null);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onLongPress() {
                tv_swipe_check.setText("Long Press");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getExternalInputControl().launchInputPicker(new Launcher.AppLaunchListener() {
                        public void onError(ServiceCommandError error) { }

                        public void onSuccess(LaunchSession object) {
                            inputPickerSession = object;
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersSwipeTop() {
                tv_swipe_check.setText("Swipe Up (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersSwipeRight() {
                tv_swipe_check.setText("Swipe Right (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersSwipeLeft() {
                tv_swipe_check.setText("Swipe Left (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersSwipeBottom() {
                tv_swipe_check.setText("Swipe Down (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersSingleTap() {
                tv_swipe_check.setText("Tap (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
                    mTV.getKeyControl().sendKeyCode(KeyCode.ENTER, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersDoubleTap() {
                tv_swipe_check.setText("Double Tap (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.ENTER, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
            public void onTwoFingersLongPress() {
                tv_swipe_check.setText("Long Press (2 fingers)");
                tv_swipe_check.startAnimation(anim);
                if (mTV != null ) {
//                    mTV.getKeyControl().sendKeyCode(KeyCode.ENTER, null);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backClick(View view) {
        finish();
    }

    public void closeClick(View view) {
        finish();
    }

    public void helpClick(View view) {
        Intent intent = new Intent(SwipeControlActivity.this, GesturesActivity.class);
        startActivity(intent);
    }
}