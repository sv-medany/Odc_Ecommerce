package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.afinal.Adapters.VerticalAdapter;
import com.example.afinal.Retrofit.RetrofitClient;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.databinding.ActivityHomeScreenBinding;
import com.example.afinal.fragments.AccountFragment;
import com.example.afinal.fragments.CartFragment;
import com.example.afinal.fragments.ExploreFragment;
import com.example.afinal.fragments.FavouriteFragment;
import com.example.afinal.fragments.ShopFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {

    VerticalAdapter verticalAdapter;
    ProgressDialog dialog;
    ActivityHomeScreenBinding binding;
    RecyclerView rec;
    SearchView searchh;

    public ProgressDialog progressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("API Fetching");
        dialog.setMessage("Loading Data......");
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1=getIntent();
        binding= ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ShopFragment());
        dialog=progressDialog();
        dialog.show();
        getData();


                binding.nav.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.shop:
                            break;
                        case R.id.exp:
                            //replaceFragment(new ExploreFragment());
                            Intent intent2 = new Intent(HomeScreen.this, ExploreScreen.class);
                            startActivity(intent2);
                            break;
                        case R.id.cart:
                            Intent intent3 = new Intent(HomeScreen.this,MyCartScreen.class);
                            startActivity(intent3);
                            break;
                        case R.id.favv:
                            Intent intent4 = new Intent(HomeScreen.this,FavoriteScreen.class);
                            startActivity(intent4);
                            break;
                        case R.id.acc:
                            Intent intent5 = new Intent(HomeScreen.this,AccountScreen.class);
                            startActivity(intent5);
                            break;
                    }
                    return true;
                });
            }


    private void getData() {

        Call<Products> call = RetrofitClient.getInstance().getMyapi().getexculusiveoffers();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(HomeScreen.this, "Code is :" +String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    return;
                }
                Products users = response.body();
                setMainCategoryRecycler(users);

                dialog.cancel();

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(HomeScreen.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
            }



    private void setMainCategoryRecycler(Products products){
        rec=findViewById(R.id.rec1);
        VerticalAdapter itemRecyclerAdapter= new VerticalAdapter(HomeScreen.this,products);
        rec.setAdapter(itemRecyclerAdapter);
        rec.setLayoutManager(new LinearLayoutManager(HomeScreen.this,RecyclerView.VERTICAL,false));


       // RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        //rec.setLayoutManager(layoutManager);
        //verticalAdapter= new VerticalAdapter(this,products);
        //rec.setAdapter(verticalAdapter);

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav,fragment);
        fragmentTransaction.commit();
    }
}