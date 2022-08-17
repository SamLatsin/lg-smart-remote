package com.sensustech.lgremote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.connectsdk.device.ConnectableDevice;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.SingletonTV;

public class AproveActivity extends AppCompatActivity {
    public ConnectableDevice mTV;
    public EditText editInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprove);
        editInput = findViewById(R.id.editInput);
        SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
        mTV = tv.getTV();
    }

    public void aproveClick(View view) {
        String value = editInput.getText().toString().trim();
        if (mTV != null) {
            mTV.sendPairingKey(value);
            finish();
        }
    }
}