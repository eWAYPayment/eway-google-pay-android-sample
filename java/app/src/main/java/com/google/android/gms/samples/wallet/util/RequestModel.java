package com.google.android.gms.samples.wallet.util;

public class RequestModel {

    private PaymentInstrument paymentInstrument;
    private Payment payment;
    private Method method;
    private TransactionType transactionType;


    public RequestModel() {
    }

    public RequestModel(PaymentInstrument paymentInstrument, Payment payment, Method method, TransactionType transactionType) {
        super();
        this.paymentInstrument = paymentInstrument;
        this.payment = payment;
        this.method = method;
        this.transactionType = transactionType;
    }

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public RequestModel withPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public RequestModel withPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public RequestModel withMethod(Method method) {
        this.method = method;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}