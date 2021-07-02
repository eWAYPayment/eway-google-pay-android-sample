package com.google.android.gms.samples.wallet.service;

import android.accounts.AuthenticatorException;
import android.icu.text.NumberFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.samples.wallet.Constants;
import com.google.android.gms.samples.wallet.util.Method;
import com.google.android.gms.samples.wallet.util.Payment;
import com.google.android.gms.samples.wallet.util.PaymentInstrument;
import com.google.android.gms.samples.wallet.util.PaymentType;
import com.google.android.gms.samples.wallet.util.RequestModel;
import com.google.android.gms.samples.wallet.util.TransactionType;
import com.google.android.gms.samples.wallet.util.WalletDetails;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class eWAYPaymentService {

    private static final String APIKEY = Constants.EWAY_API_KEY;
    private static final String PASSWORD = Constants.EWAY_PASSWORD;
    private static final String ENDPOINT = Constants.EWAY_ENDPOINT;

    static OkHttpClient client = new OkHttpClient();

    public static String MakePayment(String paymentData, String totalPrice){
        String response = "";
        try {
            String request = CreateRequest(paymentData, totalPrice);
            response = CreateHttpClientWithAuth(request);

        } catch (IOException | AuthenticatorException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String CreateHttpClientWithAuth(String json ) throws IOException, AuthenticatorException {
        AtomicReference<String> eWAYResponse = new AtomicReference<>("");
        Thread newThread = new Thread(()->{
            try {
                eWAYResponse.set(doRequest(ENDPOINT+"Transactions", json));
            } catch (IOException e) {
                e.printStackTrace();
                eWAYResponse.set(e.getMessage());
            }
        });

        newThread.start();
        while (newThread.isAlive()){
            System.out.println("Waiting...");
        }
        return eWAYResponse.get();
    }

    public static String doRequest(String url, String json) throws IOException{
        RequestBody body =   RequestBody.create(json,
                MediaType.parse("application/json"));

        Request request = new Request.Builder().url(url).
                addHeader("Authorization", Credentials.basic(APIKEY,PASSWORD)).
                addHeader("Content-Type","application/json")
                .post(body).
                        build();

        Response response = client.newCall(request).execute();

        return Objects.requireNonNull(response.body()).string();
    }

    public static String CreateRequest(String paymentData, String totalPrice) {
        String json = "";

        try {
        int amount = ConvertPrice(totalPrice);
        WalletDetails walletDetails = new WalletDetails(paymentData);
        PaymentInstrument paymentInstrument = new PaymentInstrument(PaymentType.GooglePay, walletDetails);
        Payment payment = new Payment(amount, "AUD");

        RequestModel requestModel = new RequestModel(paymentInstrument, payment,Method.ProcessPayment, TransactionType.ECom);

        json = ConvertObject(requestModel);

        return json;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private static int ConvertPrice(String totalPrice) throws ParseException {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(totalPrice);
        BigDecimal price = new BigDecimal(number.toString());

        BigDecimal priceCents = price.multiply(BigDecimal.valueOf(100));

        return (priceCents.intValue());

    }

    public static String ConvertObject(RequestModel model){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(model);
            return json;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return json;
    }
}

