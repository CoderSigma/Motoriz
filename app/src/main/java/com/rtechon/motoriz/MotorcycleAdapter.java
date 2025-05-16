package com.rtechon.motoriz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MotorcycleAdapter extends RecyclerView.Adapter<MotorcycleAdapter.ViewHolder> {

    private final List<Motorcycle> motorcycleList;
    private final Context context;

    public MotorcycleAdapter(Context context, List<Motorcycle> motorcycleList) {
        this.context = context;
        this.motorcycleList = motorcycleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_motorcycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Motorcycle motorcycle = motorcycleList.get(position);

        // Bind data to the views
        holder.modelTextView.setText(motorcycle.getModel());
        holder.yearTextView.setText("Year: " + motorcycle.getYear());
        holder.detailsTextView.setText(motorcycle.getDetails() + " Displacement.");

        // Optional: If you want to use descriptionTextView, uncomment below and ensure it's in your layout
        // holder.descriptionTextView.setText(motorcycle.getDescription());

        // Handle item click to open detail activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MotorcycleDetailActivity.class);
            intent.putExtra("motorcycle_id", motorcycle.getId());
            intent.putExtra("model", motorcycle.getModel());
            intent.putExtra("year", motorcycle.getYear());
            intent.putExtra("details", motorcycle.getDetails());
            intent.putExtra("price", motorcycle.getPrice());
            intent.putIntegerArrayListExtra("images", motorcycle.getImages());
            intent.putExtra("description", motorcycle.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return motorcycleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView modelTextView, yearTextView, detailsTextView, descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);

            // Initialize descriptionTextView only if you have it in your layout
            // descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
