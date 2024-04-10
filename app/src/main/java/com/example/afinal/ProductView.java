package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.afinal.data.fetchedData.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductView extends AppCompatActivity {
    ImageView imgages;
    ImageSwitcher imageSwitcher;
    ImageButton previous, next, add, remove;
    Button addtocart;
    ToggleButton favvv;
    TextView titlee, desc, pricee, discount, stockk, idd, ratingg, brandd, categoryy, quantity, reviews;
    private int position = 0;
    int stock;
    ArrayList<String> Images;
    int priceof1;
    String productTitle;
    String productDesc;
    String productPrice;
    String productId;
    int iddd;
    String productCategory;
    String thumbnail;
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        imageSwitcher = findViewById(R.id.imgSw);
        previous = findViewById(R.id.btnPrevious);
        next = findViewById(R.id.btnNext);
        add = findViewById(R.id.increase);
        remove = findViewById(R.id.decrease);
        titlee = findViewById(R.id.item_title);
        pricee = findViewById(R.id.item_price);
        desc = findViewById(R.id.description);
        quantity = findViewById(R.id.quantity);
        addtocart=findViewById(R.id.addtocart);
        favvv=findViewById(R.id.favvv);
        //ratingg = findViewById(R.id.rate);
        //reviews = findViewById(R.id.reviews);
        Intent i = getIntent();
        productTitle = i.getStringExtra("titlee");
        productDesc = i.getStringExtra("desc");
        productPrice = i.getStringExtra("pricee");
        thumbnail=i.getStringExtra("thumbnail");
        int productStock = i.getIntExtra("stockk",0);
        iddd = i.getIntExtra("idd",0);
        //String productRating = i.getStringExtra("ratingg");
        String productBrand = i.getStringExtra("brandd");
        productCategory = i.getStringExtra("categoryy");
        Images = i.getStringArrayListExtra("image");
        titlee.setText(productTitle);
        pricee.setText(productPrice + "$");
        priceof1= Integer.parseInt(productPrice);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LogingScreen.dbHelper.addCart(ProductView.this,LogingScreen.globaluser.getId(),LogingScreen.globaluser.getEmail(),
                        iddd,productTitle,priceof1,
                        productDesc,productCategory,thumbnail,quantity.getText().toString());
            }
        });

        desc.setText(productDesc);
        //ratingg.setText(productRating);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= Integer.parseInt(quantity.getText().toString());
                if(Integer.parseInt(quantity.getText().toString())> productStock) {
                    Toast.makeText(ProductView.this, "Maximum Stock Reached", Toast.LENGTH_SHORT).show();
                }
                else{

                    temp = temp + 1;
                    quantity.setText(Integer.toString(temp));
                    updateprice();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= Integer.parseInt(quantity.getText().toString());
                if(temp>1){temp=temp-1;}

                quantity.setText(Integer.toString(temp));
                updateprice();
            }
        });

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgVw= new ImageView(ProductView.this);
                Glide.with(ProductView.this).load(Images.get(position)).into(imgVw);
                imgVw.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imgVw.setBackground((getDrawable(R.drawable.imgswitcher_rounded)));
                return imgVw;
            }
        });
        imageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        imageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0)
                    position--;
                else if(position<0)
                    position = 0;
                ImageView imgVw= new ImageView(ProductView.this);
                Glide.with(ProductView.this).load(Images.get(position)).into((ImageView) imageSwitcher.getCurrentView());
                imgVw.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imgVw.setBackground((getDrawable(R.drawable.imgswitcher_rounded)));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<Images.size())
                    position++;
                if(position>=Images.size())
                    position = Images.size()-1;
                Glide.with(ProductView.this).load(Images.get(position)).into((ImageView) imageSwitcher.getCurrentView());

            }
        });
    }



    private void updateprice()
    {
        float itemprice= priceof1*(Float.parseFloat( quantity.getText().toString()));
        pricee.setText(Float.toString(itemprice)+" $");
    }

    public void onDefaultToggleClick(View view) {
        Toast.makeText(this,"DefaultToggleClick",Toast.LENGTH_SHORT).show();
    }

    public void onCostumeToggleClick(View view) {
        Toast.makeText(this,"CostumeToggleClick",Toast.LENGTH_SHORT).show();
        if(cont==1)
        {
            LogingScreen.favHelper.deleteItem(String.valueOf(productId));
            cont=0;
        }
        else{
            LogingScreen.favHelper.addCart(ProductView.this,LogingScreen.globaluser.getId(),LogingScreen.globaluser.getEmail(),
                    iddd,productTitle,Float.parseFloat(String.valueOf(priceof1)),
                    productDesc,productCategory,thumbnail);
            cont=1;
        }

    }
}

