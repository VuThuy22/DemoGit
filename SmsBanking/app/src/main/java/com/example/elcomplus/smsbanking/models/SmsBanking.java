package com.example.elcomplus.smsbanking.models;

public class SmsBanking {
    private String name;
    private String time;
    private String total_balance;
    private String phone;
    private String content;

    public SmsBanking(String name, String time, String total_balance,String phone, String content) {
        this.name = name;
        this.time = time;
        this.total_balance = total_balance;
        this.phone = phone;
        this.content = content;
    }
    public SmsBanking(String name, String time, String total_balance) {
        this.name = name;
        this.time = time;
        this.total_balance = total_balance;
    }


    public SmsBanking() {

    }

    public SmsBanking(String bank, String total) {
        this.name = bank;
        this.total_balance = total;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = total_balance;
    }

    @Override
    public String toString() {
        return "SmsBanking{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", total_balance='" + total_balance + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
