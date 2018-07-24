package com.example.elcomplus.demo_sharedrepreferences.screens.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elcomplus.demo_sharedrepreferences.screens.MainActivity;
import com.example.elcomplus.demo_sharedrepreferences.utils.PreferencesUtils;
import com.example.elcomplus.demo_sharedrepreferences.R;
import com.example.elcomplus.demo_sharedrepreferences.constants.Cts;
import com.example.elcomplus.demo_sharedrepreferences.utils.Utils;

import javax.xml.transform.ErrorListener;

import static com.example.elcomplus.demo_sharedrepreferences.utils.Utils.*;

public class LoginActivity extends Activity {

    EditText edt_pass, edt_user, edt_country, edt_age;
    Button btn_login;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_pass = findViewById(R.id.edt_pass);
        edt_user = findViewById(R.id.edt_user);
        edt_country = findViewById(R.id.edt_country);
        edt_age = findViewById(R.id.edt_age);
        btn_login = findViewById(R.id.btn_login);
        checkbox = findViewById(R.id.checkbox);

        if (getBool(Cts.KEY_CHECK)) {
            String user = Utils.getString(Cts.KEY_USER, "");
            String pass = Utils.getString(Cts.KEY_PASS, "");
            String country = Utils.getString(Cts.KEY_COUNTRY, "");
            int age = getInt(Cts.KEY_AGE, 0);
            edt_pass.setText(pass);
            edt_user.setText(user);
            edt_country.setText(country);
            edt_age.setText(age+"");
            checkbox.setChecked(true);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (checkLogin())
                nextActivity();
            }
        });

    }

    private void nextActivity() {
        if (checkbox.isChecked()) {
           Utils.saveString(Cts.KEY_USER,edt_user.getText().toString());
           Utils.saveString(Cts.KEY_PASS,edt_pass.getText().toString());
           Utils.saveString(Cts.KEY_COUNTRY,edt_country.getText().toString());
           Utils.saveBool(Cts.KEY_CHECK,true);
           Utils.saveInt(Cts.KEY_AGE, Integer.parseInt(edt_age.getText().toString()));
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
