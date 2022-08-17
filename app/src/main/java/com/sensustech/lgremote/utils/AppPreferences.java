package com.sensustech.lgremote.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static AppPreferences yourPreference;
    private final SharedPreferences sharedPreferences;

    public static AppPreferences getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new AppPreferences(context);
        }
        return yourPreference;
    }

    private AppPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("LGRemote",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getString(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public String getString(String key, String defValue) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, defValue);
        }
        return "";
    }

    public void saveData(String key,Boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public Boolean getBoolean(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public Boolean getBoolean(String key, Boolean defValue) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getBoolean(key, defValue);
        }
        return false;
    }

    public int getInt(String key, int defValue) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getInt(key, defValue);
        }
        return 0;
    }

    public void saveData(String key,int value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }

}
