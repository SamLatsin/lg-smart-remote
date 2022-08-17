package com.sensustech.lgremote.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.connectsdk.device.ConnectableDevice;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.SingletonTV;
import com.sensustech.lgremote.utils.RepeatListener;
import com.sensustech.lgremote.utils.TestResponseObject;

public class ControlFragment extends Fragment {
    private ImageView btn_3d;
    private ImageView upButton;
    private ImageView downButton;
    private ImageView leftButton;
    private ImageView rightButton;
    public TestResponseObject testResponse;
    private boolean state3d;
    ConnectableDevice mTV;

    public ControlFragment() {
    }

    public static ControlFragment newInstance() {
        return new ControlFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_control, container, false);
        btn_3d = root.findViewById(R.id.button3D);
        upButton = root.findViewById(R.id.buttonControlUp);
        downButton = root.findViewById(R.id.buttonControlDown);
        leftButton = root.findViewById(R.id.buttonControlLeft);
        rightButton = root.findViewById(R.id.buttonControlRight);
        state3d = false;
        final SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
        mTV = tv.getTV();
        upButton.setOnTouchListener(new RepeatListener(400, 30, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getKeyControl().up(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        downButton.setOnTouchListener(new RepeatListener(400, 30, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getKeyControl().down(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        leftButton.setOnTouchListener(new RepeatListener(400, 30, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getKeyControl().left(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        rightButton.setOnTouchListener(new RepeatListener(400, 30, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getKeyControl().right(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        btn_3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null ) {
                    if (state3d == false){
                        state3d = true;
                        mTV.getTVControl().set3DEnabled(true, null);
                    }
                    else {
                       state3d = false;
                       mTV.getTVControl().set3DEnabled(false, null);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}
