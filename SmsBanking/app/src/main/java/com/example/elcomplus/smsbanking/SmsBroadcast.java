package com.example.elcomplus.smsbanking;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elcomplus.smsbanking.database.BankDatahelper;
import com.example.elcomplus.smsbanking.database.SmsBankingDatahelper;
import com.example.elcomplus.smsbanking.models.Bank;
import com.example.elcomplus.smsbanking.models.SmsBanking;
import com.example.elcomplus.smsbanking.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SmsBroadcast extends BroadcastReceiver {
    SmsBankingDatahelper smsBankingDatahelper;
    BankDatahelper bankDatahelper;
    private static final String TAG = "SmsBroadcast";
    SmsBanking smsBanking;
    Bank bank;
    String total, mess, time, from, transaction, phone, content;
    String URL = "http://101.99.23.175:5566";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            Bundle bundle = intent.getExtras();
            smsBankingDatahelper = new SmsBankingDatahelper(context);
            bankDatahelper = new BankDatahelper(context);
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    mess = smsMessage.getDisplayMessageBody();
                    transaction = "";
                    total = "";
                    phone = "";
                    time = TimeUtils.convertLongToddMMyyyy(smsMessage.getTimestampMillis());
                    from = smsMessage.getDisplayOriginatingAddress();
                    switch (from) {
                        case "BIDV": {
                            if (mess.indexOf("NAP_LUCKYBETS_") != -1) {
                                if (mess.indexOf("TK") == 0) {
                                    for (int j = mess.indexOf("BIDV") + 5; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                                            break;
                                        transaction = transaction + mess.charAt(j);
                                    }
                                    for (int j = mess.indexOf("So du:") + 7; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                                            break;
                                        total = total + mess.charAt(j);
                                    }
                                     readContent();
                                     loadData();
                                     PostJson(URL, context);
                                     senBroadcast(context);
                                }
                            } else {

                                Intent intent1 = new Intent("showDialog");
                                context.sendBroadcast(intent1);

                            }

                            break;
                        }
                        case "Vietcombank": {
                            if (mess.indexOf("NAP_LUCKYBETS_") != -1) {
                                if (mess.indexOf("So du VCB") == 0) {
                                    for (int j = mess.indexOf("thay doi") + 9; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                                            break;
                                        transaction = transaction + mess.charAt(j);

                                    }
                                    for (int j = mess.indexOf("So du:") + 7; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n') {
                                            break;
                                        }
                                        total = total + mess.charAt(j);
                                    }
                                    readContent();
                                    loadData();
                                    senBroadcast(context);
                                }

                            } else {
                                Intent intent1 = new Intent("showDialog");
                                context.sendBroadcast(intent1);
                            }
                            break;
                        }
                        case "Sacombank":
                        case "Vietinbank": {
                            if (mess.indexOf("NAP_LUCKYBETS_") != -1) {
                                if (mess.indexOf("VietinBank") == 0) {
                                    for (int j = mess.indexOf("|GD:") + 4; j < mess.length(); j++) {
                                        if (mess.charAt(j) == 'V')
                                            break;
                                        transaction = transaction + mess.charAt(j);

                                    }
                                    for (int j = mess.indexOf("|SDC:") + 5; j < mess.length(); j++) {
                                        if (mess.charAt(j) == 'V') {
                                            break;
                                        }
                                        total = total + mess.charAt(j);
                                    }
                                    readContent();
                                    loadData();
                                    senBroadcast(context);
                                }

                            } else {
                                Intent intent1 = new Intent("showDialog");
                                context.sendBroadcast(intent1);
                            }
                            break;
                        }
                        case "Techcombank": {
                            if (mess.indexOf("NAP_LUCKYBETS_") != -1) {
                                if (mess.indexOf("TK") == 0) {
                                    for (int j = mess.indexOf("So tien GD:") + 11; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                                            break;
                                        transaction = transaction + mess.charAt(j);

                                    }
                                    for (int j = mess.indexOf("So du:") + 6; j < mess.length(); j++) {
                                        if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                                            break;
                                        total = total + mess.charAt(j);
                                    }
                                    readContent();
                                    senBroadcast(context);
                                }
                            } else {
                                Intent intent1 = new Intent("showDialog");
                                context.sendBroadcast(intent1);
                            }
                            break;
                        }
                        case "LPB": {
                            if (mess.indexOf("NAP_LUCKYBETS_") != -1) {
                                if (mess.indexOf("LPB:") == 0) {
                                    for (int j = mess.indexOf("TK") + 17; j < mess.length(); j++) {
                                        if (mess.charAt(j) == 'V')
                                            break;
                                        transaction = transaction + mess.charAt(j);
                                    }
                                    for (int j = mess.indexOf("SO DU:") + 7; j < mess.length(); j++) {
                                        if (mess.charAt(j) == 'V')
                                            break;
                                        total = total + mess.charAt(j);
                                    }
                                    readContent();
                                    loadData();
                                    senBroadcast(context);
                                }
                            } else {
                                Intent intent1 = new Intent("showDialog");
                                context.sendBroadcast(intent1);
                            }

                            break;
                        }
                    }
                }
            }
        }
    }
    public void loadData(){
        bankDatahelper.getBank(from);
        bank=new Bank();

//        if (from.length() == 3) {
//            bank = new Bank(total, "LienVietPostBank");
//        } else {
//            bank = new Bank(total, from);
//        }
        bankDatahelper.editBank(bank);
        smsBanking = new SmsBanking(from, time, transaction, phone,content);
        smsBankingDatahelper.addSms(smsBanking);
    }

    public void senBroadcast(Context context) {

        Intent intent1 = new Intent("receiverSms");
        context.sendBroadcast(intent1);
    }

    public void readContent() {
        for(int j=mess.indexOf("NAP_LUCKYBETS_");j<mess.length();j++){
            if(mess.charAt(j)==' ' || mess.charAt(j)=='\n')
                break;
            content=content+mess.charAt(j);
        }
        for (int j = mess.indexOf("NAP_LUCKYBETS_") + 14; j < mess.length(); j++) {
            if (mess.charAt(j) == ' ' || mess.charAt(j) == '\n')
                break;
            phone = phone + mess.charAt(j);
        }
    }

    public void PostJson(String url, final Context context) {
        url = url + "/api/vietlott/admin/change_balance";

        JSONObject request = new JSONObject();
        try {

            request.put("requestId", (int) System.currentTimeMillis());
            request.put("bankId", bank.getmId() );
            request.put("bankName", bank.getmName());
            Log.d(TAG, "PostJson: "+bank.getmName());
            String smsCurrent = smsBanking.getTotal_balance();
            for (int i = 0; i < smsCurrent.length(); i++) {
                if (smsCurrent.charAt(0) == '+')
                    request.put("requestType", 0);
                else
                    request.put("requestType", 1);
                break;
            }
            smsCurrent = smsCurrent.substring(1);
            smsCurrent = smsCurrent.replace(",", "");
            request.put("money", Long.parseLong(smsCurrent));
            request.put("content", smsBanking.getContent());
            request.put("account", smsBanking.getPhone());
            request.put("fullContent", mess);
            request.put("action", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest postJson = new JsonObjectRequest(Request.Method.POST, url, request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e(TAG, "onResponse: "+response.toString());
                            int result = response.getInt("result");
                            String resultDesc = response.getString("resultDesc");
                            String requestId = response.getString("requestId");
                            Log.d("request", "onResponse: "+resultDesc );
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
        }
        );
        Volley.newRequestQueue(context.getApplicationContext()).add(postJson);
    }
}
