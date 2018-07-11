package com.example.elcomplus.smsbanking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elcomplus.smsbanking.models.Bank;
import com.example.elcomplus.smsbanking.models.SmsBanking;

import java.util.ArrayList;
import java.util.Collection;

public class SmsBankingDatahelper {
    private static final String TAG = "SmsBankingDatahelper";
    private static final String SMS_TABLE_NAME = "sms";
    private static final String SMS_ID = "id";
    private static final String SMS_NAME = "ten";
    private static final String SMS_TIME = "time";
    private static final String SMS_TOTAL_BALANCE = "sodu";
    private static final String SMS_PHONE = "phone";
    private static final String SMS_CONTENT = "SMS_CONTENT";
    public static String queryDrop = "DROP TABLE " + SMS_TABLE_NAME;
    public static String query = "CREATE TABLE " + SMS_TABLE_NAME + " ( "
            + SMS_ID + " INTEGER PRIMARY KEY, "
            + SMS_NAME + " TEXT, "
            + SMS_TIME + " TEXT, "
            + SMS_PHONE + " TEXT, "
            + SMS_CONTENT + " TEXT, "
            + SMS_TOTAL_BALANCE + " TEXT ) ";

    private DataBaseHelper helper;

    public SmsBankingDatahelper(Context context) {
        helper = new DataBaseHelper(context);
    }


    public void addSms(SmsBanking sms) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SMS_NAME, sms.getName());
        values.put(SMS_TOTAL_BALANCE, sms.getTotal_balance());
        values.put(SMS_TIME, sms.getTime());
        values.put(SMS_PHONE,sms.getPhone());
        values.put(SMS_CONTENT,sms.getContent());
        db.insert(SMS_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<SmsBanking> getAll() {
        ArrayList<SmsBanking> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM " + SMS_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getAll: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                SmsBanking sms = new SmsBanking();
                sms.setName(cursor.getString(cursor.getColumnIndex(SMS_NAME)));
                sms.setTotal_balance(cursor.getString(cursor.getColumnIndex(SMS_TOTAL_BALANCE)));
                sms.setTime(cursor.getString(cursor.getColumnIndex(SMS_TIME)));
                sms.setPhone(cursor.getString(cursor.getColumnIndex(SMS_PHONE)));
                Log.e(TAG, "getAll: " + sms.toString());
                list.add(sms);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(SMS_TABLE_NAME, null, null);
        db.close();

    }
}
