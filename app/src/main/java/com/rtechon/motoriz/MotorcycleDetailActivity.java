package com.rtechon.motoriz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class MotorcycleDetailActivity extends AppCompatActivity {

    private TextView modelTextView, yearTextView, detailsTextView, priceTextView, financingTextView;
    private Button buyButton;
    private ViewPager2 viewPager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorcycle_detail);

        // Initialize Views
        modelTextView = findViewById(R.id.modelTextView);
        yearTextView = findViewById(R.id.yearTextView);
        detailsTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        financingTextView = findViewById(R.id.financingTextView);
        buyButton = findViewById(R.id.buyButton);
        viewPager = findViewById(R.id.viewPager);

        // Set up the toolbar and back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get intent extras
        Intent intent = getIntent();
        if (intent != null) {
            String model = intent.getStringExtra("model");
            String year = intent.getStringExtra("year");
            String details = intent.getStringExtra("details");
            double price = intent.getDoubleExtra("price", 0.0);
            List<Integer> imgList = intent.getIntegerArrayListExtra("images");

            if (model != null && year != null && details != null && imgList != null) {
                modelTextView.setText(model);
                yearTextView.setText("Year: " + year);
                detailsTextView.setText(details);
                priceTextView.setText("Price: ₱" + String.format("%.2f", price));
                financingTextView.setText("Financing: Available");

                // Pass images to ViewPager
                ImageAdapter adapter = new ImageAdapter(imgList);
                viewPager.setAdapter(adapter);

                buyButton.setOnClickListener(v -> {
                    Toast.makeText(this, "Redirecting to Purchase Form", Toast.LENGTH_SHORT).show();
                    Intent purchaseIntent = new Intent(this, PurchaseFormActivity.class);
                    purchaseIntent.putExtra("motorcycle_model", model);
                    purchaseIntent.putExtra("motorcycle_price", price);
                    startActivity(purchaseIntent);
                });

            } else {
                Toast.makeText(this, "Motorcycle details not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Intent data is missing", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Inner Adapter Class for ViewPager2
    public static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private final List<Integer> images;

        public ImageAdapter(List<Integer> images) {
            this.images = images;
        }

        public static class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }
    }
}
