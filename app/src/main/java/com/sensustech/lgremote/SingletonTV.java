package com.sensustech.lgremote;

import com.connectsdk.device.ConnectableDevice;

public class SingletonTV {
    ConnectableDevice mTV;

    private static final SingletonTV ourInstance = new SingletonTV();

    public static SingletonTV getInstance() {
        return ourInstance;
    }

    private SingletonTV() { }

    public void setTV(ConnectableDevice mTV) {
        this.mTV = mTV;
    }

    public ConnectableDevice getTV() {
        return mTV;
    }
}
