package com.example.elcomplus.smsbanking;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.elcomplus.smsbanking.adapters.BankAdapter;
import com.example.elcomplus.smsbanking.adapters.SmsAdapter;
import com.example.elcomplus.smsbanking.database.BankDatahelper;
import com.example.elcomplus.smsbanking.database.SmsBankingDatahelper;
import com.example.elcomplus.smsbanking.models.Bank;
import com.example.elcomplus.smsbanking.models.SmsBanking;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc_viewBank, rc_viewSms;
    TextView txt_total;
    List<Bank> list_bank;
    List<SmsBanking> list_sms;
    RecyclerView.LayoutManager linear_horizontal, linear_vertical;
    BankDatahelper bankDatahelper;
    SmsBankingDatahelper smsBankingDatahelper;
    BankAdapter bankAdapter;
    SmsAdapter smsAdapter;
    BroadcastReceiver receiver;
    SmsBanking smsBanking;
    Bank Vietcombank, Sacombank, Vietinbank, Techcombank, LienVietPostBank, BIDV;
    int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 1;
    private final int REQ_CODE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        widget();
        readBank();
        setTextTxt_total();
        registerReceiver();
        register();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                readSms();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_SMS}, REQ_CODE_SMS);
            }
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestPermissions(
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
            }

        } else {
            readSms();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE_SMS) {
            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readSms();
            } else {
                finish();
            }
        }
    }

    private void register(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("showDialog");
        receiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                showAlertDialog();
            }
        };
        registerReceiver(receiver, intentFilter);
    }
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("receiverSms");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readSms();
            }
        };
        registerReceiver(receiver, intentFilter);
    }



    public void readBank() {

        bankDatahelper = new BankDatahelper(this);
        list_bank.addAll(bankDatahelper.getAll());
        Log.d("bankkk", "readBank: " + list_bank.size());
        if (list_bank.size() <= 0){
            Vietcombank = new Bank("Vietcombank", "CN Nam HN", "Vietcombank", "0");
            bankDatahelper.addBank(Vietcombank);

            Vietinbank = new Bank(" Vietinbank", "PGD Đồng Tâm", "Vietinbank", "0");
            bankDatahelper.addBank(Vietinbank);

            Sacombank = new Bank("Sacombank ", "CN Thanh Trì", "Sacombank", "0");
            bankDatahelper.addBank(Sacombank);

            Techcombank = new Bank("Techcombank", "CN Thăng Long", "Techcombank", "0");
            bankDatahelper.addBank(Techcombank);

            LienVietPostBank = new Bank("LienVietPostBank", "PGD Thanh Nhàn", "LPV", "0");
            bankDatahelper.addBank(LienVietPostBank);

            BIDV = new Bank("BIDV", "CN Quang Trung", "BIDV", "0");
            bankDatahelper.addBank(BIDV);
        }

        rc_viewBank.setAdapter(bankAdapter);
        list_bank.clear();
        list_bank.addAll(bankDatahelper.getAll());
        bankAdapter.notifyDataSetChanged();
    }

    public void readSms() {
        list_sms.clear();
        list_sms.addAll(smsBankingDatahelper.getAll());
        list_bank.clear();
        list_bank.addAll(bankDatahelper.getAll());
        bankAdapter.notifyDataSetChanged();
        smsAdapter.notifyDataSetChanged();
        if(list_sms.size()>1){
            rc_viewSms.smoothScrollToPosition(list_sms.size()-1);
        }
        setTextTxt_total();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void setTextTxt_total() {
        List<String> list = new ArrayList<>();
        bankDatahelper = new BankDatahelper(this);
        list.addAll(bankDatahelper.getTotalBank());
        long totalBalance = 0;
        for (int i = 0; i < list.size(); i++) {
            try {
                String replacedString = list.get(i).replace(",", "");
                Log.d("list ", "setTxt_total: "+list.get(i));
                totalBalance = totalBalance + Long.parseLong(replacedString);

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        txt_total.setText(decimalFormat.format(totalBalance)+"Đ");
    }

    public void showAlertDialog(){
        final AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông báo!").setMessage("Tin nhắn không được thêm vào data của app")
                .setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void widget() {
        rc_viewBank = findViewById(R.id.rc_viewBank);
        rc_viewSms = findViewById(R.id.rc_viewSms);
        txt_total = findViewById(R.id.txt_total);
        list_bank = new ArrayList<>();
        list_sms = new ArrayList<SmsBanking>();
        smsBanking = new SmsBanking();
        bankAdapter = new BankAdapter(list_bank);
        smsAdapter = new SmsAdapter(list_sms, this);
        smsBankingDatahelper = new SmsBankingDatahelper(this);
        linear_horizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linear_vertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        rc_viewBank.setLayoutManager(linear_horizontal);
        rc_viewSms.setLayoutManager(linear_vertical);
        rc_viewSms.setAdapter(smsAdapter);

    }
}
