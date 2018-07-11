package com.example.elcomplus.smsbanking.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elcomplus.smsbanking.R;
import com.example.elcomplus.smsbanking.database.BankDatahelper;
import com.example.elcomplus.smsbanking.models.Bank;
import com.example.elcomplus.smsbanking.models.SmsBanking;
import com.example.elcomplus.smsbanking.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SmsBanking> list;
    private List<Bank> Banklist, PhoneList;
    BankDatahelper bankDatahelper;

    public SmsAdapter(List<SmsBanking> list_sms, Context context) {
        this.list = list_sms;
        bankDatahelper = new BankDatahelper(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Holder holder1 = (Holder) holder;
        Log.d("size", "onBindViewHolder: " + list.get(position).getName());
        holder1.txt_bank.setText(list.get(position).getName());
        holder1.txt_time.setText(list.get(position).getTime());
        String total_balance=list.get(position).getTotal_balance();
        char total=total_balance.charAt(0);
        if(total=='-'){
            holder1.txt_transaction.setTextColor(Color.BLUE);
        }
        else{
            holder1.txt_transaction.setTextColor(Color.RED);
        }
        holder1.txt_transaction.setText(total_balance);
        holder1.setIsRecyclable(false);
        Long time = TimeUtils.convertStringToLong(list.get(position).getTime());
        Boolean isToday = TimeUtils.checkToday(time);
        if (isToday) {
            holder1.image.setImageResource(R.mipmap.ic_time);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txt_bank, txt_transaction, txt_time;
        ImageView image;

        public Holder(View itemView) {
            super(itemView);
            txt_bank = (TextView) itemView.findViewById(R.id.txt_bank);
            txt_transaction = (TextView) itemView.findViewById(R.id.txt_transaction);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
