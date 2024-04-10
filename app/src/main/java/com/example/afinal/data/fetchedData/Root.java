package com.example.afinal.data.fetchedData;

import java.util.ArrayList;

public class Root {
    public ArrayList<User> users;
    public int total;
    public int skip;
    public int limit;

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }
}
