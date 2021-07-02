package com.google.android.gms.samples.wallet.util;

public class PaymentInstrument {

    private PaymentType paymentType;
    private WalletDetails walletDetails;


    public PaymentInstrument() {
    }


    public PaymentInstrument(PaymentType paymentType, WalletDetails walletDetails) {
        super();
        this.paymentType = PaymentType.GooglePay;
        this.walletDetails = walletDetails;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.GooglePay;
    }

    public PaymentInstrument withPaymentType(PaymentType paymentType) {
        this.paymentType = PaymentType.GooglePay;
        return this;
    }

    public WalletDetails getWalletDetails() {
        return walletDetails;
    }

    public void setWalletDetails(WalletDetails walletDetails) {
        this.walletDetails = walletDetails;
    }

    public PaymentInstrument withWalletDetails(WalletDetails walletDetails) {
        this.walletDetails = walletDetails;
        return this;
    }

}