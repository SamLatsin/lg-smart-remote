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

public class RemoteFragment extends Fragment {
    private ImageView btn_mute;
    private ImageView volUpButton;
    private ImageView volDownButton;
    private ImageView chUpButton;
    private ImageView chDownButton;
    private boolean mute_state;
    public TestResponseObject testResponse;
    ConnectableDevice mTV;

    public RemoteFragment() {
    }
    public static RemoteFragment newInstance() {
        return new RemoteFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_remote, container, false);
        btn_mute = root.findViewById(R.id.buttonMute);
        volUpButton = root.findViewById(R.id.buttonVolPlus);
        volDownButton = root.findViewById(R.id.buttonVolMinus);
        chUpButton = root.findViewById(R.id.buttonChPlus);
        chDownButton = root.findViewById(R.id.buttonChMinus);
        final SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
        mTV = tv.getTV();
        mute_state = false;
        btn_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null ) {
                    if (mute_state == false){
                        btn_mute.setImageResource(R.drawable.b_mute_h);
                        mute_state = true;
                        mTV.getVolumeControl().setMute(true, null);
                    }
                    else {
                        btn_mute.setImageResource(R.drawable.b_mute);
                        mute_state = false;
                        mTV.getVolumeControl().setMute(false, null);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }

            }
        });
        volUpButton.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getVolumeControl().volumeUp(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        volDownButton.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getVolumeControl().volumeDown(null);
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeDown);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        chUpButton.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getTVControl().channelUp(null);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        chDownButton.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV = tv.getTV();
                if (mTV != null){
                    mTV.getTVControl().channelDown(null);
                }
                else {
                    Toast.makeText(getContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        return root;
    }
}
