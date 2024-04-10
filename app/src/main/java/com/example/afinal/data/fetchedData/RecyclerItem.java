package com.example.afinal.data.fetchedData;

import java.util.ArrayList;

public class RecyclerItem {
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;



    public ArrayList<String> getImages() {
        return images;
    }

    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public int getPrice() {return price;}

    public Double getDiscountPercentage() {return discountPercentage;}

    public Double getRating() {return rating;}

    public int getStock() {return stock;}

    public String getBrand() {return brand;}

    public String getCategory() {return category;}

    public String getThumbnail() {return thumbnail;}
}

