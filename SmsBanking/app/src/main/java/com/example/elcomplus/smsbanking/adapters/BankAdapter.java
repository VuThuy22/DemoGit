package com.example.elcomplus.smsbanking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elcomplus.smsbanking.R;
import com.example.elcomplus.smsbanking.models.Bank;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bank> list;

    public BankAdapter(List<Bank> list_bank) {
        this.list = list_bank;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Holder holder1 = (Holder) holder;
        holder1.txt_bank.setText(list.get(position).getmName());
        holder1.txt_totalBalance.setText(list.get(position).getmTotalBalance() + "Đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txt_total, txt_totalBalance, txt_bank;

        public Holder(View itemView) {
            super(itemView);
            txt_bank = (TextView) itemView.findViewById(R.id.txt_bank);
            txt_total = (TextView) itemView.findViewById(R.id.txt_total);
            txt_totalBalance = (TextView) itemView.findViewById(R.id.txt_totalBalance);
        }
    }
}
