package com.example.elcomplus.smsbanking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String SMS_BANK_DATA = "SMS_BANK_DATA";
    private static final int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, SMS_BANK_DATA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BankDatahelper.query);
        db.execSQL(SmsBankingDatahelper.query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BankDatahelper.queryDrop);
        db.execSQL(SmsBankingDatahelper.queryDrop);
        onCreate(db);
    }

}
