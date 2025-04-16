package com.rtechon.motoriz;

public class Motorcycle {
    String model, year, details;
    private int id;
    private double price;

    // Constructor with id, model, year, details, and price
    public Motorcycle(int id, String model, String year, String details, double price) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.details = details;
        this.price = price;

    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for model
    public String getModel() {
        return model;
    }

    // Getter for year
    public String getYear() {
        return year;
    }

    // Getter for details
    public String getDetails() {
        return details;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Method to get the price formatted as pesos (₱)
    public String getFormattedPrice() {
        return "₱" + String.format("%.2f", price);
    }
}
