package com.example.afinal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.afinal.LogingScreen;
import com.example.afinal.R;
import com.example.afinal.data.localData.Cart;
import com.example.afinal.data.localData.Favourites;
//import com.example.ecommerce.main.repository.models.remotedata.Products;
//import com.example.ecommerce.main.ui.ItemPage;
//import com.example.ecommerce.main.ui.Mainscreen;
//import com.example.ecommerce.main.ui.SuccessLogin;
import com.example.afinal.Helpers.Helper;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder>{
    Context ctx;
    ArrayList<Favourites> favouriteslist;

    public FavAdapter(Context ctx, ArrayList<Favourites> favouriteslist) {
        this.ctx = ctx;
        this.favouriteslist = favouriteslist;
    }

    @NonNull
    @Override
    public FavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.fav_cell,parent,false);
        FavAdapter.MyViewHolder viewHolder = new  FavAdapter.MyViewHolder(recyclerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.MyViewHolder holder, int position) {
        holder.favitemprice.setText(favouriteslist.get(position).getPrice()+"$");
        holder.favtitle.setText(favouriteslist.get(position).getTitle());
        Glide.with(ctx).load(favouriteslist.get(position).getImages()).into(holder.favimage);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, ItemPage.class);
                int idchosen=favouriteslist.get(holder.getAdapterPosition()).getId();
                intent.putExtra("id2",idchosen);
                ctx.startActivity(intent);


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return favouriteslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView favimage,itemremove;
        TextView favtitle,favitemprice , favdes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.favimage=itemView.findViewById(R.id.favimagee);
            this.favtitle=itemView.findViewById(R.id.coketitlee);
            this.favdes=itemView.findViewById(R.id.cokedess);
            this.favitemprice=itemView.findViewById(R.id.cokepricee);

        }
    }
}