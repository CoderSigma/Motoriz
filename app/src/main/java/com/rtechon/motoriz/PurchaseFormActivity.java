package com.rtechon.motoriz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PurchaseFormActivity extends AppCompatActivity {

    private EditText inputName, inputEmail, inputAge, inputBirthdate, inputAddress, inputIncome, amountToPay;
    private Button submitButton;

    private static final String PAYMONGO_API_KEY = "sk_test_4D5ef3VqrdryX5hbSCJUYG3K"; // 🔒 Replace with your actual PayMongo secret key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_form);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputAge = findViewById(R.id.inputAge);
        inputBirthdate = findViewById(R.id.inputBirthdate);
        inputAddress = findViewById(R.id.inputAddress);
        inputIncome = findViewById(R.id.inputIncome);
        amountToPay = findViewById(R.id.amountToPay);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            if (validateForm()) {
                createPaymentLink();
            }
        });
    }

    private boolean validateForm() {
        if (inputName.getText().toString().isEmpty() ||
                inputEmail.getText().toString().isEmpty() ||
                inputAge.getText().toString().isEmpty() ||
                inputBirthdate.getText().toString().isEmpty() ||
                inputAddress.getText().toString().isEmpty() ||
                inputIncome.getText().toString().isEmpty() ||
                amountToPay.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createPaymentLink() {
        OkHttpClient client = new OkHttpClient();

        // Convert PHP to centavos
        double amountPhp = Double.parseDouble(amountToPay.getText().toString());
        int amountCentavos = (int) (amountPhp * 100);

        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = "{ \"data\": { \"attributes\": { " +
                "\"amount\": " + amountCentavos + "," +
                "\"description\": \"Motorcycle Installment Payment\"," +
                "\"remarks\": \"Installment Payment\"" +
                "} } }";

        String credentials = PAYMONGO_API_KEY + ":";
        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", basicAuth)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(PurchaseFormActivity.this, "Failed to connect to PayMongo", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(() ->
                            Toast.makeText(PurchaseFormActivity.this, "PayMongo Error: " + response.code(), Toast.LENGTH_SHORT).show());
                    return;
                }

                String responseBody = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    String checkoutUrl = jsonObject.getJSONObject("data")
                            .getJSONObject("attributes")
                            .getString("checkout_url");

                    // Open in browser
                    runOnUiThread(() -> {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl));
                        startActivity(browserIntent);
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(PurchaseFormActivity.this, "Failed to parse PayMongo response", Toast.LENGTH_SHORT).show());
                    e.printStackTrace();
                }
            }
        });
    }
}
