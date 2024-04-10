package com.example.afinal.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.afinal.data.localData.Cart;

import java.util.ArrayList;

public class CartHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "CartDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Cart_Details";
    private static final String ID_COL = "id";
    private static final String ID_User = "User_id";
    private static final String EMAIL_COL = "User_email";
    private static final String Item_Id = "Item_id";
    private static final String Item_Title = "Item_title";
    private static final String Item_Price = "Item_price";
    private static final String Item_Desc = "Item_desc";
    private static final String Item_Category = "Item_category";
    private static final String Item_Quantity = "Item_quantity";
    private static final String Item_Images="Item_images";



    public CartHelper(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                "("+ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ID_User+" TEXT,"
                +EMAIL_COL+" TEXT,"
                +Item_Id+" TEXT UNIQUE,"
                +Item_Title+" TEXT,"
                +Item_Price+" TEXT,"
                +Item_Desc+" TEXT,"
                +Item_Category+" TEXT,"
                +Item_Quantity+" TEXT,"
                +Item_Images+" TEXT)";

        Log.d("onUpgrade","create");

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        Log.d("onUpgrade","db upgrade");
        onCreate(db);
    }
    public void addCart(Context ctx, int userid, String email, int id, String title, float price,
                        String description, String category, String images, String quantity){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_User,userid);
        values.put(EMAIL_COL,email.toLowerCase());
        values.put(Item_Id,id);
        values.put(Item_Title,title);
        values.put(String.valueOf(Item_Price),price);
        values.put(Item_Desc,description);
        values.put(Item_Category,category);
        values.put(Item_Images,images);
        values.put(Item_Quantity,quantity);



        try {
            db.insertWithOnConflict(TABLE_NAME, null, values, 0);
            Toast.makeText(ctx, "item added to cart", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            String[] error = e.getMessage().split("[.]")[1].split("[ ]");
            if(error[0].equals(Item_Id)){
                String temp=(Integer.toString((Integer.parseInt(  values.get(Item_Quantity).toString())-1)));
                String send = Integer.toString (Integer.parseInt(temp)+Integer.parseInt(quantity));
                Toast.makeText(ctx, "Quantity changed in cart", Toast.LENGTH_LONG).show();
                UpdateItemquantity( userid, email, id,  title,  price,
                        description, category,  images,  send);
            }
        }

        db.close();

    }

    public int getAllCart(){

        String query = "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }
    public ArrayList<Cart> getAllItems(String Searchvalue, String searCOL){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Cart> item=new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{ ID_COL,
                        ID_User,
                        EMAIL_COL,
                        Item_Id,
                        Item_Title,
                        Item_Price,
                        Item_Desc,
                        Item_Category,
                        Item_Quantity,
                        Item_Images},
                searCOL + " LIKE?",
                new String[]{"%"+Searchvalue+"%"},null,null,null);
        if (cursor.moveToFirst())
        {
            do {
                item.add(new Cart(Integer.parseInt( cursor.getString(1)),
                        cursor.getString(2),
                        Integer.parseInt(  cursor.getString(3)),
                        cursor.getString(4),
                        Float.parseFloat(  cursor.getString(5)),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(9),
                        Integer.parseInt ( cursor.getString(8))
                ));
            }
            while (cursor.moveToNext());

        }
        return  item;


    }
    public void UpdateItemquantity(int userid, String email, int id, String title, float price,
                                   String description, String category, String images, String quantity)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID_User ,userid);
        contentValues.put(EMAIL_COL,email);
        contentValues.put(Item_Id ,id);
        contentValues.put(Item_Title,title);
        contentValues.put(Item_Price ,price);
        contentValues.put(Item_Desc,description);
        contentValues.put(Item_Category  ,category);
        contentValues.put(Item_Images,images);
        contentValues.put(Item_Quantity,quantity);
        db.update(TABLE_NAME, contentValues, Item_Id +"="+ id,null );

    }

    public void UpdateItem(String userid, String email, String id, String title, float price,
                           String description, String category, String images, String quantity)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID_User ,userid);
        contentValues.put(EMAIL_COL,email);
        contentValues.put(Item_Id ,id);
        contentValues.put(Item_Title,title);
        contentValues.put(Item_Price ,price);
        contentValues.put(Item_Desc,description);
        contentValues.put(Item_Category  ,category);
        contentValues.put(Item_Images,images);
        contentValues.put(Item_Quantity,quantity);
        db.update(TABLE_NAME, contentValues, Item_Id +"="+ id,null );

    }
    public void deleteItem(String itemdelete) {


        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "Item_id=?", new String[]{itemdelete});
        db.close();
    }
    public void Emptycart() {


        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
    }
}