package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.Adapters.CategoryFilterAdapter;
import com.example.afinal.Retrofit.RetrofitClient;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.RecyclerItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemByCategory extends AppCompatActivity {
    String Categoryy;
    RecyclerView recyclerView;
    TextView cat;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_by_category);
        Intent i =getIntent();
        Categoryy=i.getStringExtra("category");
        recyclerView=findViewById(R.id.rec2);
        cat=findViewById(R.id.cat);
        dialog=progressDialog();
        dialog.show();
        getData();

    }
    public ProgressDialog progressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("API Fetching");
        dialog.setMessage("Loading Data......");
        dialog.setCancelable(false);
        return dialog;
    }
    private void getData() {

        Call<Products> call = RetrofitClient.getInstance().getMyapi().getbyCategory(Categoryy);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ItemByCategory.this, "Code is :" +String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    return;
                }

                Products users = response.body();
                cat.setText(users.getProducts().get(0).getCategory());
                CategoryFilterAdapter categoryFilterAdapter= new CategoryFilterAdapter(users,ItemByCategory.this);
                RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(categoryFilterAdapter);

                dialog.cancel();

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(ItemByCategory.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}