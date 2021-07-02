package com.google.android.gms.samples.wallet.util;

public class Payment {

    private int TotalAmount;
    private String currencyCode;


    public Payment() {
    }

    public Payment(int totalAmount, String currencyCode) {
        super();
        this.TotalAmount = totalAmount;
        this.currencyCode = currencyCode;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.TotalAmount = totalAmount;
    }

    public Payment withTotalAmount(int totalAmount) {
        this.TotalAmount = totalAmount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Payment withCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }



}