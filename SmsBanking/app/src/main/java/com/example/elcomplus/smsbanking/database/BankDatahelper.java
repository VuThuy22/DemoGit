package com.example.elcomplus.smsbanking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elcomplus.smsbanking.models.Bank;

import java.util.ArrayList;

public class BankDatahelper {
    private static final String BANK_TABLE_NAME = "bank";
    private static final String BANK_ID = "id";
    private static final String BANK_NAME = "ten";
    private static final String BANK_ACCOUNT = "stk";
    private static final String BANK_TOTAL_BALANCE = "sodu";
    private static final String BANK_BRANCH = "chinhanh";
    private static final String BANK_PHONE = "sdt";
    private static final String BANK_USER = "user";
    private static final String BANK_UPDATE_STATUS = "BANK_UPDATE_STATUS";

    public static String query = "CREATE TABLE " + BANK_TABLE_NAME + " ( " + BANK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + BANK_NAME + " TEXT, " + BANK_ACCOUNT + " TEXT, " + BANK_USER + " TEXT, " + BANK_PHONE + " TEXT, " + BANK_TOTAL_BALANCE + " TEXT, " + BANK_BRANCH + " TEXT , " +BANK_UPDATE_STATUS+" TEXT ) ";
    public static String queryDrop = "DROP TABLE IF EXISTS " + BANK_TABLE_NAME;

    private DataBaseHelper helper;

    public BankDatahelper(Context context) {
        helper = new DataBaseHelper(context);
    }


    public void addBank(Bank bank) {
        SQLiteDatabase DB = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BANK_TOTAL_BALANCE, bank.getmTotalBalance());
        values.put(BANK_NAME, bank.getmName());
        values.put(BANK_ACCOUNT, bank.getmAccount());
        values.put(BANK_BRANCH, bank.getmBranch());
        values.put(BANK_PHONE, bank.getmPhone());
        values.put(BANK_USER, bank.getmUser());
        values.put(BANK_UPDATE_STATUS,bank.getmStatus());
        DB.insert(BANK_TABLE_NAME, null, values);
        DB.close();
    }

    public ArrayList<Bank> getAll() {
        ArrayList<Bank> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getAll: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                Bank bank = new Bank();
                bank.setmName(cursor.getString(cursor.getColumnIndex(BANK_NAME)));
                bank.setmTotalBalance(cursor.getString(cursor.getColumnIndex(BANK_TOTAL_BALANCE)));
                list.add(bank);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getTotalBank() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getcolum: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex(BANK_TOTAL_BALANCE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getBankPhone() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getcolum: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void editBank(Bank bank) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BANK_TOTAL_BALANCE, bank.getmTotalBalance());
        db.update(BANK_TABLE_NAME, values, BANK_NAME + "=?", new String[]{String.valueOf(bank.getmName())});
        db.close();
    }

    public Bank getBank(String BankPhone){
        Bank bank=new Bank();
        SQLiteDatabase db=helper.getReadableDatabase();
        String query="SELECT * FROM " +BANK_TABLE_NAME ;
        Cursor cursor=db.rawQuery(query, new String[]{BANK_PHONE + "=?" + BankPhone});
        if(cursor.moveToFirst()){
            bank.setmId(cursor.getInt(cursor.getColumnIndex(BANK_PHONE)));
            bank.setmPhone(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
            bank.setmName(cursor.getString(cursor.getColumnIndex(BANK_NAME)));
            bank.setmTotalBalance(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
        }
        cursor.close();
        return bank;
    }
}
