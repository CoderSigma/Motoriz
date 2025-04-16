package com.rtechon.motoriz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.List;

public class MotorcycleAdapter extends RecyclerView.Adapter<MotorcycleAdapter.ViewHolder> {

    private List<Motorcycle> motorcycleList;
    private Context context;

    // Constructor with context to handle click events
    public MotorcycleAdapter(Context context, List<Motorcycle> motorcycleList) {
        this.context = context;
        this.motorcycleList = motorcycleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motorcycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Motorcycle motorcycle = motorcycleList.get(position);
        holder.modelTextView.setText(motorcycle.getModel());
        holder.yearTextView.setText(String.format("Year: %s", motorcycle.getYear()));
        holder.detailsTextView.setText(String.format("%s Displacement.", motorcycle.getDetails()));

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> {
            // Handle the item click and pass the motorcycle's data to the next activity
            Intent intent = new Intent(context, MotorcycleDetailActivity.class);
            intent.putExtra("motorcycle_id", motorcycle.getId());  // Assuming Motorcycle has getId() method
            intent.putExtra("model", motorcycle.getModel());
            intent.putExtra("year", motorcycle.getYear());
            intent.putExtra("details", motorcycle.getDetails());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return motorcycleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView modelTextView, yearTextView, detailsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
        }
    }
}
