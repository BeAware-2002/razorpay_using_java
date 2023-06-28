package com.example.paymentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button payment_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout.preload(getApplicationContext());

        payment_button = findViewById(R.id.payment_button);

        payment_button.setOnClickListener(view -> {
            // enter the amount you want
            makePayment("150");
        });

    }

    public void makePayment(String amount) {

        final Activity activity = this;

        Checkout checkout = new Checkout();
        // test api key
        checkout.setKeyID("rzp_test_TZROr70aCOzBAR");

        double finalAmount = Float.parseFloat(amount)*100;

        try {
            JSONObject object = new JSONObject();
            object.put("name", "ABC Office");
            object.put("description", "Reference No. #144521");
            object.put("image", R.drawable.ic_launcher_background);
            object.put("theme.color", "#ff0000");
            object.put("currency", "INR");
            object.put("amount", finalAmount + "");
            object.put("prefill.email", "user@gmail.com");
            object.put("prefill.contact", "6485123458");

            checkout.open(activity, object);


        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error in payment", Toast.LENGTH_SHORT).show();

    }
}