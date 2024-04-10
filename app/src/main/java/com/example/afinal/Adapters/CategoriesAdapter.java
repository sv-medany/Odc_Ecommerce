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
import com.example.afinal.ItemByCategory;
import com.example.afinal.ProductView;
import com.example.afinal.R;
import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter  extends RecyclerView.Adapter<CategoriesAdapter.MainViewHolder> implements Filterable {
    private List<String>Categories;
    private List<String> clone;


    Context context;

    public CategoriesAdapter(List<String>c, Context context) {
        Categories=c;
        this.context = context;
        clone= new ArrayList<>(Categories); //create clone

    }


    @NonNull
    @Override
    public CategoriesAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, null);
        CategoriesAdapter.MainViewHolder m = new CategoriesAdapter.MainViewHolder(v);
        return m;
    }
/*   @Override
    public int getItemViewType(int position) {
        if (position == 1)
            return 1;
        return 0;

    }*/

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MainViewHolder holder, int position) {
        holder.grid_title.setText(Categories.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posnow=holder.getAdapterPosition();
                Intent i = new Intent(context, ItemByCategory.class);
                i.putExtra("category",Categories.get(posnow));
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Categories.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <String> newList = new ArrayList<>(); // create new list
            if(constraint == null || constraint.length() ==0){
                newList.addAll(clone);
            }else{
                String FilterPattern = constraint.toString().toLowerCase().trim();
                for(String i : clone){
                    if(i.toLowerCase().contains(FilterPattern)){
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
            Categories.clear();
            Categories.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static final class MainViewHolder extends RecyclerView.ViewHolder{
        TextView grid_title;
        ImageView grid_image;
        CardView card2;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            grid_image=itemView.findViewById(R.id.grid_img);
            grid_title=itemView.findViewById(R.id.grid_title);
            card2=itemView.findViewById(R.id.card2);

        }
    }


}



