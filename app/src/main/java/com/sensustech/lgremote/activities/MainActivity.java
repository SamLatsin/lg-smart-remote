package com.sensustech.lgremote.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.DiscoveryManagerListener;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.webos.WebOSTVServiceSocketClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sensustech.lgremote.SingletonTV;
import com.sensustech.lgremote.fragments.ControlFragment;
import com.sensustech.lgremote.fragments.RemoteFragment;
import com.sensustech.lgremote.fragments.SettingsFragment;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.utils.AppPreferences;
import com.connectsdk.service.capability.KeyControl.KeyCode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.LaunchSession;
import com.sensustech.lgremote.utils.TestResponseObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DiscoveryManagerListener {
    private FrameLayout frame;
    private BottomNavigationView bottomNav;
    Timer mTimer;
    connectTask connectTask;
    public TestResponseObject testResponse;
    public LaunchSession inputPickerSession;
    public ConnectableDevice mTV;
    boolean power_state = true;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.host_fragment);
        bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(this);
        loadFragment(RemoteFragment.newInstance());
        DiscoveryManager.init(getApplicationContext());
        startSearch();
    }

    private void startSearch() {
        DiscoveryManager.getInstance().registerDefaultDeviceTypes();
        DiscoveryManager.getInstance().setPairingLevel(DiscoveryManager.PairingLevel.ON);
        DiscoveryManager.getInstance().addListener(this);
        DiscoveryManager.getInstance().start();
    }

    protected void onResume() {
        super.onResume();
        Log.e("Resumed", "True");
        SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
        mTV = tv.getTV();
        if (mTV != null) {
            mTV.setPairingType(DeviceService.PairingType.PIN_CODE);
            mTV.connect();
            Log.e("Resumed", mTV.getId());
            Log.e("Resumed", mTV.getIpAddress());
            Log.e("Resumed", mTV.getFriendlyName());
            Log.e("Resumed", mTV.getConnectedServiceNames());
            Log.e("Resumed", mTV.getModelName());
            Log.e("Resumed", mTV.getModelNumber());
            mTimer = new Timer();
            connectTask = new connectTask();
            mTimer.schedule(connectTask, 1000);
        }
    }

    public void captionClick(View view) {
        if (mTV != null){
//            Log.e("cap", mTV.getCapabilities().toString());
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void TVClick(View view) {
        if (mTV != null){
            mTV.getExternalInputControl().launchInputPicker(new Launcher.AppLaunchListener() {

                public void onError(ServiceCommandError error) { }

                public void onSuccess(LaunchSession object) {
                    inputPickerSession = object;
                    testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.InputPickerVisible);
                }
            });
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void exitClick(View view) {
        if (mTV != null){
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void rewindClick(View view) {
        if (mTV != null){
            mTV.getMediaControl().rewind(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void pauseClick(View view) {
        if (mTV != null){
            mTV.getMediaControl().pause(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void playClick(View view) {
        if (mTV != null){
            mTV.getMediaControl().play(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void forwardClick(View view) {
        if (mTV != null){
            mTV.getMediaControl().fastForward(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyOkClick(View view) {
        if (mTV != null){
            mTV.getKeyControl().ok(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyHomeClick(View view) {
        if (mTV != null){
            mTV.getKeyControl().home(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyBackClick(View view) {
        if (mTV != null){
            mTV.getKeyControl().back(null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key1Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_1, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key2Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_2, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key3Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_3, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key4Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_4, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key5Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_5, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key6Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_6, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key7Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_7, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key8Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_8, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key9Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_9, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void key0Click(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.NUM_0, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyListClick(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.DASH, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyInfoClick(View view) {
        if (mTV != null){
            mTV.getKeyControl().sendKeyCode(KeyCode.ENTER, null);
            testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void keyPowerClick(View view) {
        if (mTV != null){

            if (power_state == true) {
                mTV.getPowerControl().powerOff(null);
                testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                power_state = false;
            }
            else {
                mTV.getPowerControl().powerOn(null);
                testResponse =  new TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.VolumeUp);
                power_state = true;
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "Device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void premiumClick(View view) {
        Intent intent = new Intent(MainActivity.this, PremiumActivity.class);
        startActivity(intent);
    }

    public void keyboardClick(View view) {
        Intent intent = new Intent(MainActivity.this, KeyboardActivity.class);
        startActivity(intent);
    }

    public void swipeControlClick(View view) {
        Intent intent = new Intent(MainActivity.this, SwipeControlActivity.class);
        startActivity(intent);
    }

    public void aproveClick(View view) {
        Intent intent = new Intent(MainActivity.this, AproveActivity.class);
        startActivity(intent);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_remote:
                loadFragment(RemoteFragment.newInstance());
                return true;
            case R.id.navigation_control:
                loadFragment(ControlFragment.newInstance());
                return true;
            case R.id.navigation_settings:
                loadFragment(SettingsFragment.newInstance());
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.host_fragment, fragment);
        ft.commit();
    }

    @Override
    public void onDeviceAdded(DiscoveryManager manager, ConnectableDevice device) {
        if (device != null && device.getConnectedServiceNames() != null && device.getModelName() != null ) {
            if (device.getModelName().toLowerCase().contains("lg") && device.getConnectedServiceNames().toLowerCase().contains("webos")) {
                Log.e("added", device.getIpAddress());
                Log.e("added", device.getId());
                Log.e("comp", AppPreferences.getInstance(MainActivity.this).getString("deviceID"));
                Log.e("comp", AppPreferences.getInstance(MainActivity.this).getString("deviceIp"));
                if (device.getId().equals(AppPreferences.getInstance(MainActivity.this).getString("deviceID")) || device.getIpAddress().equals(AppPreferences.getInstance(MainActivity.this).getString("deviceIp"))) {
                    Log.e("debug", device.getId());
                    SingletonTV tv = com.sensustech.lgremote.SingletonTV.getInstance();
                    tv.setTV(device);
                    onResume();
                }
            }
        }
    }

    @Override
    public void onDeviceUpdated(DiscoveryManager manager, ConnectableDevice device) {

    }

    @Override
    public void onDeviceRemoved(DiscoveryManager manager, ConnectableDevice device) {

    }

    @Override
    public void onDiscoveryFailed(DiscoveryManager manager, ServiceCommandError error) {

    }

    class connectTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (!connected) {
                        if (WebOSTVServiceSocketClient.getResponseMessage().contains("registered")) {
                            if (connected == false) {
                                Toast.makeText(getApplicationContext(), "Device connected", Toast.LENGTH_SHORT).show();
                                connected = true;
                            }
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, AproveActivity.class));
                        }
                    }
                }
            });
        }
    }
}