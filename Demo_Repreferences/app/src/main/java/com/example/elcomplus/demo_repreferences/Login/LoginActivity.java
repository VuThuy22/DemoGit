package com.example.elcomplus.demo_repreferences.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.elcomplus.demo_repreferences.MainActivity;
import com.example.elcomplus.demo_repreferences.R;
import com.example.elcomplus.demo_repreferences.Utils.Utils;
import com.example.elcomplus.demo_repreferences.constains.Cts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elcomplus.demo_repreferences.Utils.Utils.getBoolean;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvUser)
    EditText tvUser;
    @BindView(R.id.tvPass)
    EditText tvPass;
    @BindView(R.id.tvAddress)
    EditText tvAddress;
    @BindView(R.id.tvAge)
    EditText tvAge;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(getBoolean(Cts.KEY_CHECK)){
            String user=Utils.getString(Cts.KEY_USER,"");
            String address=Utils.getString(Cts.KEY_ADDRESS,"");
            String pass=Utils.getString(Cts.KEY_PASS,"");
            String age=Utils.getInt(Cts.KEY_AGE,0)+"";
            tvUser.setText(user);
            tvPass.setText(pass);
            tvAddress.setText(address);
            tvAge.setText(age);
            checkbox.setChecked(true);
        }
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
       if(checkbox.isChecked())
       {
           Utils.setString(Cts.KEY_USER,tvUser.getText().toString());
           Utils.setString(Cts.KEY_PASS,tvPass.getText().toString());
           Utils.setString(Cts.KEY_ADDRESS,tvAddress.getText().toString());
           Utils.setInt(Cts.KEY_AGE, Integer.parseInt(tvAge.getText().toString()));
           Utils.setBoolean(Cts.KEY_CHECK,true);
       }
       Intent intent =new Intent(LoginActivity.this, MainActivity.class);
       startActivity(intent);

    }
}
