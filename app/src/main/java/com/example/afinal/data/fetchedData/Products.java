package com.example.afinal.data.fetchedData;

import java.util.ArrayList;

public class Products {
    public ArrayList<RecyclerItem> products;
    public int total;
    public int skip;
    public int limit;

    public Products(ArrayList<RecyclerItem> products, int total, int skip, int limit) {
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public ArrayList<RecyclerItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<RecyclerItem> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
