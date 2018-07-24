package com.example.elcomplus.demo_sharedrepreferences.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.elcomplus.demo_sharedrepreferences.utils.PreferencesUtils;
import com.example.elcomplus.demo_sharedrepreferences.R;
import com.example.elcomplus.demo_sharedrepreferences.constants.Cts;
import com.example.elcomplus.demo_sharedrepreferences.utils.Utils;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tvName);

        tvName.setText(getString(R.string.txt_hello_user, Utils.getString(Cts.KEY_USER,"")));
    }
}
