package com.example.afinal.Helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.afinal.LogingScreen;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.User;
import com.example.afinal.data.localData.Cart;
import com.example.afinal.data.localData.Favourites;
import com.example.afinal.data.localData.Order;
import com.google.common.reflect.TypeToken;
import com.google.firestore.admin.v1.Index;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Helper {
    public static ArrayList<Cart> temp=new ArrayList<Cart>();
    public static ArrayList<Favourites> favList=new ArrayList<Favourites>();
    private static float totalprice=0;
    private static int totalquantity=0;
    public  static  String titles;
    private  static int orderindex=0;
    public  static  ArrayList<Order> orders=new ArrayList<>();

    public static ArrayList<Order> getOrders() {
        orders.clear();
        //retrieveordrer();
        return orders;
    }

    public static void AddOrders(Order nwordr) {
        titles="";
        for (int i=0;i<nwordr.getCart().size();i++)
        {
            String temp=nwordr.getCart().get(i).getTitle();
            titles=titles.concat("\n"+temp);

        }
        orderindex+=1;
        nwordr.setPrice(Float.toString( totalprice));
        nwordr.setIndex(orderindex);
        nwordr.setTitles(titles);
        orders.add(nwordr);
    }
    public static void updateorder()
    {
        Gson gson = new Gson();
        String json = gson.toJson(orders);
        //LogingScreen.editor.putString(LogingScreen.order, json);
        LogingScreen. editor.apply();

    }
   /* public  static void retrieveordrer()

    {
        String check=LogingScreen.sharedPreferences.getString(LogingScreen.order,null);
        if(!(check==null)) {
            Gson gson = new Gson();
            String json = LogingScreen.sharedPreferences.getString(LogingScreen.order, "");
            Type userListType = new TypeToken<ArrayList<Order>>() {}.getType();
            orders = gson.fromJson(json, userListType);
            orderindex = (orders.get(orders.size() - 1).getIndex()) ;
        }

    }*/


    //returns total quantity of all products placed in the cart
    public static int getTotalquantity() {
        return totalquantity;
    }
    //sets total quantity of all products placed in the cart
    public static void setTotalquantity(int totalquantity) {
        Helper.totalquantity = totalquantity;
    }

    //get the static data total price used in cart and checkout screens
    public static float getTotalprice() {
        return totalprice;
    }

    public static void setTotalprice(float totalprice) {
        Helper.totalprice = totalprice;
    }
    //used to add the active user details fetched from api to the shared prefrences data
    public static void setuser()
    {

        String name= LogingScreen.sharedPreferences.getString(LogingScreen.name,"name");
        String email= LogingScreen.sharedPreferences.getString(LogingScreen.emailuser,"emailuser");
        int id =Integer.parseInt( LogingScreen.sharedPreferences.getString(LogingScreen.id,"id"));

        LogingScreen.globaluser =new User(id,name,email);

    }

    public  ProgressDialog myProgressDialog(Context ctx) {
        ProgressDialog dialog = new ProgressDialog(ctx);
        dialog.setTitle("Fetching API");
        dialog.setMessage("Loading Data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return dialog;
    }
    public AlertDialog myAlertDialog(Context ctx, String title, String message, Context target)
    {
        AlertDialog dialog= new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ctx, target.getClass());
                        dialog.dismiss();
                        ctx.startActivity(intent);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


        return dialog; }
    public  static ArrayList<Cart> GetCart()
    {
        int count=LogingScreen.dbHelper.getAllCart();

        temp=LogingScreen.dbHelper.getAllItems(String.valueOf(1),String.valueOf(1));

        return temp;
    }

    public  static ArrayList<Favourites> Getfav()
    {
        int count=LogingScreen.favHelper.getAllFav();

        favList=LogingScreen.favHelper.getAllItems(String.valueOf(1),String.valueOf(1));

        return favList;
    }

    public static String replaceLastFour(String s) {
        int length = s.length();
        //Check whether or not the string contains at least four characters; if not, this method is useless
        if (length < 4) return "Error: The provided string is not greater than four characters long.";
        return s.substring(0, length - 4) + "****";
    }
}