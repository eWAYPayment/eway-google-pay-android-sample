package com.google.android.gms.samples.wallet.activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.android.gms.samples.wallet.R;
import com.google.android.gms.samples.wallet.util.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class ResultActivity extends Activity {
    TextView text;
    private static DecimalFormat priceFormat = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageView greenTick = (ImageView)findViewById(R.id.greenTick);
        ImageView redCross = (ImageView)findViewById(R.id.redCross);
        TextView result = (TextView)findViewById(R.id.detailsResult);
        TextView transactionId = (TextView)findViewById(R.id.detailTransactionId);
        TextView totalAmount = (TextView)findViewById(R.id.detailTotalAmount);
        TextView paymentType = (TextView)findViewById(R.id.detailsPaymentType);
        TextView errors = (TextView)findViewById(R.id.detailErrors);
        TextView errorsHeader = (TextView)findViewById(R.id.errorHeader);

        greenTick.setVisibility(ImageView.INVISIBLE);
        redCross.setVisibility(ImageView.INVISIBLE);

        Intent intent = getIntent();
        String str = intent.getStringExtra("response");

        try {
            assert str != null;
            JSONObject json = new JSONObject(str);
            JSONObject payment = json.getJSONObject("payment");
            String resultStr = json.getString("transactionStatus");

            String price = payment.getString("totalAmount");
            double finalPrice =  Double.parseDouble(price) / 100;


            if (resultStr.equalsIgnoreCase("Success")){
                greenTick.setVisibility(ImageView.VISIBLE);
                errors.setVisibility(TextView.INVISIBLE);
                errorsHeader.setVisibility(TextView.INVISIBLE);


                result.setText(resultStr);
                transactionId.setText(json.getString("transactionId"));
                totalAmount.setText(String.format("$%s", priceFormat.format(finalPrice)));
                paymentType.setText(json.getString("method"));
            }
            else if(resultStr.equalsIgnoreCase(("Error"))) {
                redCross.setVisibility(ImageView.VISIBLE);

                result.setText(resultStr);
                transactionId.setText(json.getString("transactionId"));
                totalAmount.setText(String.format("$%s", priceFormat.format(finalPrice)));
                paymentType.setText(json.getString("method"));

                errors.setText(json.getString("errors"));
            }
            else{
                redCross.setVisibility(ImageView.VISIBLE);
                transactionId.setText(json.getString("transactionId"));
                totalAmount.setText(String.format("$%s", priceFormat.format(finalPrice)));
                paymentType.setText(json.getString("method"));

                result.setText("Error");
            }
        } catch (JSONException e) {
            errors.setText(e.getMessage());
            redCross.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}