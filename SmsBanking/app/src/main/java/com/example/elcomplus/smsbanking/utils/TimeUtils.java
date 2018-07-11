package com.example.elcomplus.smsbanking.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String convertLongToddMMyyyy(Long timeSTamp){
        try {
            Date date = new Date(timeSTamp);
            DateFormat formatter = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
            return formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static Long convertStringToLong(String string_date){

        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        try {
            Date d = f.parse(string_date);
            long milliseconds = d.getTime();
            return  milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
            return Long.valueOf(21/12/2007);
        }
    }
    public static boolean checkToday(long smsTimeInMilis) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE) ) {
            return true;
        }
        return false;
    }
}
