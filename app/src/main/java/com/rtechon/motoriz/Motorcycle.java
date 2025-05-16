package com.rtechon.motoriz;

import java.util.ArrayList;

public class Motorcycle {
    private int id;
    private String model;
    private String year;
    private String details;
    private double price;
    private ArrayList<Integer> images;

    private String description;

    public Motorcycle(int id, String yamahaYtx, String number, String details, double price, ArrayList<Integer> images) {
        this.id = id;
        this.model = yamahaYtx;
        this.year = number;
        this.details = details;
        this.price = price;
        this.images = images;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getDetails() {
        return details;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }
}
