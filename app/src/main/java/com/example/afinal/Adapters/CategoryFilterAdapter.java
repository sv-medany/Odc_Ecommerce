package com.example.afinal.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.afinal.ProductView;
import com.example.afinal.R;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.MainViewHolder> implements Filterable {
    private Products allProductslist;
    private List<RecyclerItem> clone;

    Context context;

    public CategoryFilterAdapter(Products allProductslist, Context context) {
        this.allProductslist = allProductslist;
        this.context = context;
        clone= new ArrayList<>(allProductslist.getProducts()); //create clone

    }


    @NonNull
    @Override
    public CategoryFilterAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_cell, null);
        CategoryFilterAdapter.MainViewHolder m = new CategoryFilterAdapter.MainViewHolder(v);
        return m;
    }
/*   @Override
    public int getItemViewType(int position) {
        if (position == 1)
            return 1;
        return 0;

    }*/

    @Override
    public void onBindViewHolder(@NonNull CategoryFilterAdapter.MainViewHolder holder, int position) {
        holder.productname.setText(allProductslist.getProducts().get(position).getTitle());

        holder.productspecification.setText(allProductslist.getProducts().get(position).getBrand());
        holder.Price.setText(String.valueOf(allProductslist.getProducts().get(position).getPrice())+"$");
        Glide.with(context).load(allProductslist.getProducts().get(position).getThumbnail()).
                into(holder.prod_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posnow=holder.getAdapterPosition();
                Intent i = new Intent(context, ProductView.class);
                i.putStringArrayListExtra("image",allProductslist.getProducts().get(posnow).getImages());
                i.putExtra("titlee",allProductslist.getProducts().get(posnow).getTitle());
                i.putExtra("desc",allProductslist.getProducts().get(posnow).getDescription());
                i.putExtra("pricee",String.valueOf(allProductslist.getProducts().get(posnow).getPrice()));
                i.putExtra("discount",allProductslist.getProducts().get(posnow).getDiscountPercentage());
                i.putExtra("stockk",allProductslist.getProducts().get(posnow).getStock());
                i.putExtra("idd",allProductslist.getProducts().get(posnow).getId());
                i.putExtra("ratingg",String.valueOf(allProductslist.getProducts().get(posnow).getRating()));
                i.putExtra("brandd",allProductslist.getProducts().get(posnow).getBrand());
                i.putExtra("categoryy",allProductslist.getProducts().get(posnow).getCategory());

                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return allProductslist.getProducts().size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <RecyclerItem> newList = new ArrayList<>(); // create new list
            if(constraint == null || constraint.length() ==0){
                newList.addAll(clone);
            }else{
                String FilterPattern = constraint.toString().toLowerCase().trim();
                for(RecyclerItem i : clone){
                    if(i.getTitle().toLowerCase().contains(FilterPattern)){
                        newList.add(i);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = newList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allProductslist.getProducts().clear();
            allProductslist.getProducts().addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static final class MainViewHolder extends RecyclerView.ViewHolder{
           TextView productname,productspecification,Price;
           ImageView prod_image,btnnn;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_image=itemView.findViewById(R.id.prod_image);
            productname=itemView.findViewById(R.id.productname);
            productspecification=itemView.findViewById(R.id.productspecification);
            Price=itemView.findViewById(R.id.Price);
            btnnn=itemView.findViewById(R.id.btnnn);
        }
    }


}


