package com.sensustech.lgremote.utils;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private float startY;
    private float startX;
    private static final int TRESHOLD = 100;
    private int pointers_count = 0;
    private long start_time = 0;
    private int taps = 0;
    private boolean longOne = false;
    private boolean longTwo = false;
    private final Handler handler = new Handler();
    private final Runnable mDoublePressOneFinger = new Runnable() {
        public void run() {
//            Log.d("action", "single tap 1");
            onSingleTap();
            taps = 0;
        }
    };
    private final Runnable mDoublePressTwoFingers = new Runnable() {
        public void run() {
//            Log.d("action", "single tap 2");
            onTwoFingersSingleTap();
            taps = 0;
        }
    };
    Runnable mLongPressedOneFinger = new Runnable() {
        public void run() {
//            Log.d("action", "Long press 1");
            onLongPress();
            longOne = true;
        }
    };
    Runnable mLongPressedTwoFingers = new Runnable() {
        public void run() {
//            Log.d("action", "Long press 2");
            onTwoFingersLongPress();
            longTwo = true;
        }
    };

    public void oneFingerUpHandler(MotionEvent event) {
        if (longOne == false) {
            taps++;
            if (taps == 1) {
                start_time = event.getEventTime();
                handler.postDelayed(mDoublePressOneFinger, ViewConfiguration.getDoubleTapTimeout());
            }
            else if (taps == 2 && event.getEventTime() - start_time < ViewConfiguration.getDoubleTapTimeout()) {
//                Log.d("action", "double tap 1");
                onDoubleTap();
                handler.removeCallbacks(mDoublePressOneFinger);
                taps = 0;
            }
        }
        longOne = false;
    }

    public void twoFingerUpHandler(MotionEvent event) {
        if (longTwo == false) {
            taps++;
            if (taps == 1) {
                start_time = event.getEventTime();
                handler.postDelayed(mDoublePressTwoFingers, ViewConfiguration.getDoubleTapTimeout());
            }
            else if (taps == 2 && event.getEventTime() - start_time < ViewConfiguration.getDoubleTapTimeout()) {
//                Log.d("action", "double tap 2");
                onTwoFingersDoubleTap();
                handler.removeCallbacks(mDoublePressTwoFingers);
                taps = 0;
            }
        }
        longTwo = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                handler.postDelayed(mLongPressedOneFinger, ViewConfiguration.getLongPressTimeout());
                startY = event.getY(0);
                startX = event.getX(0);
                break;
            case MotionEvent.ACTION_UP:
                if (pointers_count < 2) {
                    handler.removeCallbacks(mLongPressedOneFinger);
                    if (Math.abs(startY - event.getY(0)) > Math.abs(startX - event.getX(0))) {
                        if(Math.abs(startY - event.getY(0)) > TRESHOLD)
                        {
                            if(startY > event.getY(0))
                            {
                                pointers_count = 0;
//                                Log.d("action", "swipe up");
                                onSwipeTop();
                            }
                            else
                            {
                                pointers_count = 0;
//                                Log.d("action", "swipe down");
                                onSwipeBottom();
                            }
                        }
                        else {
                            oneFingerUpHandler(event);
                        }
                    }
                    else {
                        if(Math.abs(startX - event.getX(0)) > TRESHOLD)
                        {
                            if(startX > event.getX(0))
                            {
                                pointers_count = 0;
//                                Log.d("action", "swipe left");
                                onSwipeLeft();
                            }
                            else {
                                pointers_count = 0;
//                                Log.d("action", "swipe right");
                                onSwipeRight();
                            }
                        }
                        else {
                            oneFingerUpHandler(event);
                        }
                    }
                }
                else {
                    handler.removeCallbacks(mLongPressedTwoFingers);
                    pointers_count = 0;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                handler.removeCallbacks(mLongPressedOneFinger);
                handler.postDelayed(mLongPressedTwoFingers, ViewConfiguration.getLongPressTimeout());
                pointers_count = 2;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                handler.removeCallbacks(mLongPressedTwoFingers);
                if (Math.abs(startY - event.getY(0)) > Math.abs(startX - event.getX(0))) {
                    if(Math.abs(startY - event.getY(0)) > TRESHOLD)
                    {
                        if(startY > event.getY(0))
                        {
//                            Log.d("action", "swipe up 2");
                            onTwoFingersSwipeTop();
                        }
                        else
                        {
//                            Log.d("action", "swipe down 2");
                            onTwoFingersSwipeBottom();
                        }
                    }
                    else {
                        twoFingerUpHandler(event);
                    }
                }
                else {
                    if(Math.abs(startX - event.getX(0)) > TRESHOLD)
                    {
                        if(startX > event.getX(0))
                        {
//                            Log.d("action", "swipe left 2");
                            onTwoFingersSwipeLeft();
                        }
                        else
                        {
//                            Log.d("action", "swipe right 2");
                            onTwoFingersSwipeRight();
                        }
                    }
                    else {
                        twoFingerUpHandler(event);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                handler.removeCallbacks(mLongPressedTwoFingers);
                handler.removeCallbacks(mLongPressedOneFinger);
                break;
        }
        return true;
    }

    public void onSingleTap() {
    }
    public void onLongPress() {
    }

    public void onDoubleTap() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

    public void onTwoFingersSingleTap() {
    }
    public void onTwoFingersLongPress() {
    }

    public void onTwoFingersDoubleTap() {
    }

    public void onTwoFingersSwipeRight() {
    }

    public void onTwoFingersSwipeLeft() {
    }

    public void onTwoFingersSwipeTop() {
    }

    public void onTwoFingersSwipeBottom() {
    }
}
