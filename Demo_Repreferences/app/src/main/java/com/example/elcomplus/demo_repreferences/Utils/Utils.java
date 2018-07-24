package com.example.elcomplus.demo_repreferences.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import com.example.elcomplus.demo_repreferences.MyApp;

public class Utils  {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private   static void init()
    {
        if(preferences==null)
        {
            preferences= MyApp.context.getSharedPreferences("com.example.elcomplus.demo_repreferences", Context.MODE_PRIVATE);
            editor=preferences.edit();
        }
    }

    public static void setString(String key, String value)
    {
        init();
        editor.putString(key,value);
        editor.commit();
    }
    public static void setInt(String key, int value)
    {
        init();
        editor.putInt(key,value);
        editor.commit();
    }

    public static void setBoolean(String key, Boolean value)
    {
        init();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static String getString(String key, String def)
    {
        init();
        return preferences.getString(key, def);
    }

    public static int getInt(String key, int value)
    {
        init();
        return preferences.getInt(key, value);
    }
    public  static Boolean getBoolean(String key)
    {
        init();
        return preferences.getBoolean(key,false);
    }

}
