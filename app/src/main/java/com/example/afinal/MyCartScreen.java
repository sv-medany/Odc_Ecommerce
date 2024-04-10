package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.afinal.Adapters.CartAdapter;
import com.example.afinal.Helpers.CartHelper;
import com.example.afinal.data.fetchedData.RecyclerItem;
import com.example.afinal.data.localData.Cart;
import com.example.afinal.databinding.ActivityFavoriteScreenBinding;
import com.example.afinal.databinding.ActivityMyCartScreenBinding;
import com.example.afinal.fragments.AccountFragment;

import java.util.List;

public class MyCartScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    Button checkout,placeorder;
    TextView t1,quantity;
    ImageButton xx;
    public static float sum=0;
    ActivityMyCartScreenBinding binding3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_screen);
        recyclerView=findViewById(R.id.cartrec);
        checkout=findViewById(R.id.checkout);
        List<Cart> cartss= new CartHelper(MyCartScreen.this).getAllItems("1","1");
        CartAdapter main2= new CartAdapter(cartss,MyCartScreen.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCartScreen.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(main2);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog=new AlertDialog.Builder(MyCartScreen.this).create();
                View v=getLayoutInflater().inflate(R.layout.activity_mainn3,null);
                quantity=v.findViewById(R.id.quantity);
                t1=v.findViewById(R.id.total_cost);
                /*for (int i=0;i<cartss.size();i++){
                    String q1=quantity.getText().toString();
                    int x=Integer.parseInt(q1);
                    sum+=cartss.get(i).getPrice()*x;
                }*/
                t1.setText(String.valueOf(sum));
                placeorder=v.findViewById(R.id.placeorder);
                xx=v.findViewById(R.id.xx);
                placeorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =new Intent(MyCartScreen.this,Congrats.class);
                        startActivity(i);
                    }
                });
                xx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


                alertDialog.setView(v);
                alertDialog.show();
            }
        });
        binding3= ActivityMyCartScreenBinding.inflate(getLayoutInflater());
        binding3.nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.shop:
                    Intent intent1=new Intent(MyCartScreen.this,HomeScreen.class);
                    startActivity(intent1);
                    break;
                case R.id.exp:

                    Intent intent2=new Intent(MyCartScreen.this,ExploreScreen.class);
                    startActivity(intent2);
                    break;
                case  R.id.cart:
                    Intent intent3 = new Intent(MyCartScreen.this,MyCartScreen.class);
                    startActivity(intent3);
                    break;
                case  R.id.favv:
                    Intent intent4 = new Intent(MyCartScreen.this,FavoriteScreen.class);
                    startActivity(intent4);
                    break;
                case  R.id.acc:
                    Intent intent5 = new Intent(MyCartScreen.this,AccountScreen.class);
                    startActivity(intent5);
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav,fragment);
        fragmentTransaction.commit();
    }
    }

