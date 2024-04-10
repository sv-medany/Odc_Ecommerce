package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.afinal.Adapters.CategoriesAdapter;
import com.example.afinal.Adapters.CategoryFilterAdapter;
import com.example.afinal.Retrofit.RetrofitClient;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.databinding.ActivityExploreScreenBinding;
import com.example.afinal.databinding.ActivityHomeScreenBinding;
import com.example.afinal.fragments.AccountFragment;
import com.example.afinal.fragments.CartFragment;
import com.example.afinal.fragments.ExploreFragment;
import com.example.afinal.fragments.FavouriteFragment;
import com.example.afinal.fragments.ShopFragment;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreScreen extends AppCompatActivity {
    ProgressDialog dialog;
    private List<String>Categories;
    ActivityExploreScreenBinding binding1;
    RecyclerView recyclerVieww;
    CategoriesAdapter main1;
    SearchView search;
    int realvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_explore_screen);
        recyclerVieww = findViewById(R.id.grid_viewww);
        search=findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                main1.getFilter().filter(newText);
                if(main1.getItemCount()!=0 ) {
                    Toast.makeText(ExploreScreen.this,"Found" + String.valueOf(main1.getItemCount()) + " Results",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(ExploreScreen.this,"No Results Found ",Toast.LENGTH_SHORT).show();
                }
                if(search.getQuery().toString().isEmpty()){
                    Toast.makeText(ExploreScreen.this,"Found"+" Results",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        binding1= ActivityExploreScreenBinding.inflate(getLayoutInflater());
        //setContentView(binding1.getRoot());
        dialog = progressDialog();
        dialog.show();
        getCategoriess();
        binding1.nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.shop:
                    Intent intent1=new Intent(ExploreScreen.this,HomeScreen.class);
                    startActivity(intent1);
                    break;
                case R.id.exp:

                    Intent intent2=new Intent(ExploreScreen.this,ExploreScreen.class);
                    startActivity(intent2);
                    getCategoriess();
                    break;
                case  R.id.cart:
                    Intent intent3 = new Intent(ExploreScreen.this,MyCartScreen.class);
                    startActivity(intent3);
                    break;
                case  R.id.favv:
                    Intent intent4 = new Intent(ExploreScreen.this,FavoriteScreen.class);
                    startActivity(intent4);
                    break;
                case  R.id.acc:
                    Intent intent5 = new Intent(ExploreScreen.this,AccountScreen.class);
                    startActivity(intent5);
                    break;
            }
            return true;
        });
    }



    public ProgressDialog progressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("API Fetching");
        dialog.setMessage("Loading Data......");
        dialog.setCancelable(false);
        return dialog;
    }
    public void getCategoriess() {

        Call<List<String>> call = RetrofitClient.getInstance().getMyapi().getCategories();
        call.enqueue(new Callback <List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ExploreScreen.this, "Code is :" +String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    return;
                }
                List<String> Categories = response.body();
                for (int i=0;i<Categories.size();i++){
                    Log.e ("heyyyyy",Categories.get(i));
                }

                main1= new CategoriesAdapter(Categories,ExploreScreen.this);
                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExploreScreen.this);
                RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerVieww.setLayoutManager(layoutManager);
                recyclerVieww.setAdapter(main1);
                dialog.cancel();


            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ExploreScreen.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav,fragment);
        fragmentTransaction.commit();
    }
}