package com.example.elcomplus.demo_sharedrepreferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.elcomplus.demo_sharedrepreferences.MyAPP;

public class PreferencesUtils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static void init() {
        if (preferences == null) {
            preferences = MyAPP.context.getSharedPreferences("com.example.elcomplus.demo_sharedrepreferences", Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    public static void saveString(String key, String value) {
        init();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key, String def) {
        init();
        return preferences.getString(key, def);
    }

    public static void saveBool(String key, boolean value){
        init();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean getBool(String key){
        init();
        return preferences.getBoolean(key, false);
    }
}
