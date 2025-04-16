package com.rtechon.motoriz;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MotorcycleDetailActivity extends AppCompatActivity {

    private TextView modelTextView, yearTextView, detailsTextView, priceTextView, financingTextView;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorcycle_detail);

        // Initialize Views
        modelTextView = findViewById(R.id.modelTextView);
        yearTextView = findViewById(R.id.yearTextView);
        detailsTextView = findViewById(R.id.detailsTextView);
        priceTextView = findViewById(R.id.priceTextView);
        financingTextView = findViewById(R.id.financingTextView);
        buyButton = findViewById(R.id.buyButton);

        // Set up the toolbar and back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Display back button

        // Get the motorcycle ID passed via the Intent
        int motorcycleId = getIntent().getIntExtra("motorcycle_id", -1);

        // Fetch motorcycle details using the ID (this could be from a database, API, etc.)
        if (motorcycleId != -1) {
            Motorcycle motorcycle = getMotorcycleById(motorcycleId);
            modelTextView.setText(motorcycle.getModel());
            yearTextView.setText("Year: " + motorcycle.getYear());
            detailsTextView.setText(motorcycle.getDetails());
            priceTextView.setText("Price: " + motorcycle.getFormattedPrice());
            financingTextView.setText("Financing: Available");
        }

        // Handle Buy button click
        buyButton.setOnClickListener(v -> {
            // Here, you can add logic to redirect to the purchase form activity
            Toast.makeText(this, "Redirecting to Purchase Form", Toast.LENGTH_SHORT).show();
            // Start a new activity for the purchase form (you can create a new activity for that)
        });
    }

    // Simulating a method to get motorcycle by ID (you'd fetch this data from a database or API)
    private Motorcycle getMotorcycleById(int id) {
        // Replace this with actual data fetching logic
        return new Motorcycle(id, "YAMAHA YTX", "2023", "125 CC Displacement", 150000.0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle the back button press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
