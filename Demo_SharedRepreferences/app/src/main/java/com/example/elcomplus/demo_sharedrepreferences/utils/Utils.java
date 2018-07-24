package com.example.elcomplus.demo_sharedrepreferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.elcomplus.demo_sharedrepreferences.MyAPP;


public class Utils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static void init(){
        if(preferences==null)
        {
            preferences= MyAPP.context.getSharedPreferences("com.example.elcomplus.demo_sharedrepreferences.utils", Context.MODE_PRIVATE);
            editor=preferences.edit();
        }
    }

    public static void saveString(String key,String value)
    {
        init();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getString(String key, String def)
    {
        init();
        return preferences.getString(key,"");

    }
    public  static void saveInt(String key, int value)
    {
        init();
        editor.putInt(key,value);
        editor.commit();
    }

    public static int getInt(String key, int def)
    {
        init();
        return preferences.getInt(key,def);
    }

    public  static void saveBool(String key, boolean value)
    {
        init();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public  static boolean getBool(String key)
    {
        init();
        return  preferences.getBoolean(key,false);
    }

}
