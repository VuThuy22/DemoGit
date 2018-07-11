package com.example.elcomplus.smsbanking.models;

public class Bank {
    private int mId;
    private String mName;
    private String mAccount;
    private String mBranch;
    private String mTotalBalance;
    private String mPhone;
    private String mUser;
    private int mStatus;

    public Bank(int mId, String mName, String mAccount, String mBranch, String mTotalBalance,String mUser,String mPhone, int mStatus) {
        this.mId = mId;
        this.mName = mName;
        this.mAccount = mAccount;
        this.mBranch = mBranch;
        this.mTotalBalance = mTotalBalance;
        this.mUser=mUser;
        this.mPhone=mPhone;
        this.mStatus=mStatus;
    }

    public Bank() {

    }

    public Bank(String totalBalance, String name) {
        this.mName=name;
        this.mTotalBalance=totalBalance;
    }
    public Bank(String name,String branch, String phone,String totalBalance) {
        this.mName=name;
        this.mTotalBalance=totalBalance;
        this.mPhone=phone;
        this.mBranch=branch;
    }

    public Bank(int id, String phone, String name, String totalBalance){
        this.mId=id;
        this.mName=name;
        this.mTotalBalance=totalBalance;
        this.mPhone=phone;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAccount() {
        return mAccount;
    }

    public void setmAccount(String mAccount) {
        this.mAccount = mAccount;
    }

    public String getmBranch() {
        return mBranch;
    }

    public void setmBranch(String mBranch) {
        this.mBranch = mBranch;
    }

    public String getmTotalBalance() {
        return mTotalBalance;
    }

    public void setmTotalBalance(String mTotalBalance) {
        this.mTotalBalance = mTotalBalance;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mAccount='" + mAccount + '\'' +
                ", mBranch='" + mBranch + '\'' +
                ", mTotalBalance='" + mTotalBalance + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mUser='" + mUser + '\'' +
                ", mStatus=" + mStatus +
                '}';
    }
}
