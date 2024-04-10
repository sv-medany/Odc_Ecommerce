package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.afinal.Adapters.CartAdapter;
import com.example.afinal.Adapters.FavAdapter;
import com.example.afinal.Helpers.CartHelper;
import com.example.afinal.Helpers.FavHelper;
import com.example.afinal.data.localData.Cart;
import com.example.afinal.data.localData.Favourites;
import com.example.afinal.databinding.ActivityExploreScreenBinding;
import com.example.afinal.databinding.ActivityFavoriteScreenBinding;
import com.example.afinal.fragments.AccountFragment;

import java.util.ArrayList;
import java.util.List;

public class FavoriteScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    ActivityFavoriteScreenBinding binding2;
    Button addcartt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_screen);
        recyclerView=findViewById(R.id.favrec);
        addcartt=findViewById(R.id.addcartt);
        ArrayList<Favourites> favvs= new FavHelper(FavoriteScreen.this).getAllItems("1","1");
        FavAdapter main3= new FavAdapter(FavoriteScreen.this,favvs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoriteScreen.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(main3);
        addcartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<favvs.size();i++) {
                    LogingScreen.dbHelper.addCart(FavoriteScreen.this, LogingScreen.globaluser.getId(), LogingScreen.globaluser.getEmail(),
                            favvs.get(i).getId(), favvs.get(i).getTitle(), favvs.get(i).getPrice(),favvs.get(i).getDescription()
                            , favvs.get(i).getCategory(), favvs.get(i).getImages(), "1");
                    MyCartScreen.sum+=favvs.get(i).getPrice();
                }
            }
        });
        binding2= ActivityFavoriteScreenBinding.inflate(getLayoutInflater());
        binding2.nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.shop:
                    Intent intent1=new Intent(FavoriteScreen.this,HomeScreen.class);
                    startActivity(intent1);
                    break;
                case R.id.exp:

                    Intent intent2=new Intent(FavoriteScreen.this,ExploreScreen.class);
                    startActivity(intent2);
                    break;
                case  R.id.cart:
                    Intent intent3 = new Intent(FavoriteScreen.this,MyCartScreen.class);
                    startActivity(intent3);
                    break;
                case  R.id.favv:
                    Intent intent4 = new Intent(FavoriteScreen.this,FavoriteScreen.class);
                    startActivity(intent4);
                    break;
                case  R.id.acc:
                    Intent intent5 = new Intent(FavoriteScreen.this,AccountScreen.class);
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
