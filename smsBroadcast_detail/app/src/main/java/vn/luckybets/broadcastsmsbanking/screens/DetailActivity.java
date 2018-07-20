package vn.luckybets.broadcastsmsbanking.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.luckybets.broadcastsmsbanking.R;
import vn.luckybets.broadcastsmsbanking.utils.TextUtils;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_id)
    TextView txtId;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_content)
    TextView txtContent;
    @BindView(R.id.txt_fullContent)
    TextView txtFullContent;
    @BindView(R.id.txt_action)
    TextView txtAction;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.txt_account)
    TextView txtAccount;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.txt_moneytrans)
    TextView txtMoneytrans;
    @BindView(R.id.txt_time)
    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        String phone = intent.getStringExtra("phone");
        int action = intent.getIntExtra("action", -1);
        int status = intent.getIntExtra("status", -1);
        String content = intent.getStringExtra("content");
        String fullContent = intent.getStringExtra("fullcontent");
        String account = intent.getStringExtra("account");
        double balance = intent.getDoubleExtra("balance", 0);
        double moneytrans = intent.getDoubleExtra("moneytrans", 0);
        String time = intent.getStringExtra("time");
        txtAccount.setText(account);
        if(action==1)
            txtAction.setText("Nạp tiền");
        else
            txtAction.setText("Không làm gì");

        if(status==0)
            txtStatus.setText("Yêu cầu thành công");
        else if(status==-1)
            txtStatus.setText("Lỗi hệ thống");
        else
            txtStatus.setText("Yêu cầu chưa được thực hiện");

        txtId.setText(""+id);
        txtContent.setText(""+content);
        txtFullContent.setText(""+fullContent);
        txtMoneytrans.setText(TextUtils.getStringCoin(moneytrans));
        txtBalance.setText(TextUtils.getStringCoin(balance));
        txtPhone.setText(""+phone);
        txtTime.setText(""+time);
    }

}
