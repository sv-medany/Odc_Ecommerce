package com.example.afinal.Adapters;
import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MainViewHolder> {
    private Context context;
    private Products products;
    private Products clone;
    private String[]names;

    public VerticalAdapter(Context context, Products products) {
        this.context = context;
        this.products = products;
        this.clone=new Products((ArrayList)products.getProducts().clone(),products.getTotal(),products.getSkip(), products.getLimit());
        names= new String[]{"Exclusive Offers", "Best Sellings", "New Arrivals",""};
    }

    @NonNull
    @Override
    public VerticalAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_cell,null);
        MainViewHolder m = new MainViewHolder(v);
        return m;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.categoryTitle.setText(names[position]);
        setCategoryItemRecycler(holder.itemRecycler,products);
        Log.d("hh", String.valueOf(position));
        /*if(position==0)
        {
         clone.getProducts().clear();
        for(int i=0;i<10;i++){
            clone.getProducts().add(products.getProducts().get(i));
        }
         // clone.setProducts(p);
            setCategoryItemRecycler(holder.itemRecycler,clone);
        }
        else if(position==1)
        {
            clone.getProducts().clear();
            for(int i=10;i<20;i++){
                clone.getProducts().add(products.getProducts().get(i));
            }
            // clone.setProducts(p);
            setCategoryItemRecycler(holder.itemRecycler,clone);
        }
        else if (position==2){
            clone.getProducts().clear();
            for(int i=20;i<30;i++){
                clone.getProducts().add(products.getProducts().get(i));
            }
            // clone.setProducts(p);
            setCategoryItemRecycler(holder.itemRecycler,clone);
        }

       // setCategoryItemRecycler(holder.itemRecycler,products);
      //  if (products.getProducts().get(position) != null) {


       // }*/
    }

    @Override
    public int getItemCount() {

        return 4;

    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;
        RecyclerView itemRecycler;


        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle=itemView.findViewById(R.id.title);
            itemRecycler=itemView.findViewById(R.id.recycler_item);

        }
    }
    private void setCategoryItemRecycler(RecyclerView recyclerView , Products products){
        MainRecyclerAdapter itemRecyclerAdapter= new MainRecyclerAdapter(products,context);
        recyclerView.setAdapter(itemRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));



    }


}
