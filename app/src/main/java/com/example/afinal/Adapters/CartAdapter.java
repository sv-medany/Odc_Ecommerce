package com.example.afinal.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.afinal.Helpers.Helper;
import com.example.afinal.ItemByCategory;
import com.example.afinal.LogingScreen;
import com.example.afinal.MyCartScreen;
import com.example.afinal.R;
import com.example.afinal.data.localData.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MainViewHolder>{
    private List<Cart> carts;
    Context context;
    public static float totalprice;
    public static int totalquantity;

    public CartAdapter(List<Cart> c, Context context) {
        carts=c;
        this.context = context;

    }


    @NonNull
    @Override
    public CartAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_cell, null);
        CartAdapter.MainViewHolder m = new CartAdapter.MainViewHolder(v);
        return m;
    }
/*   @Override
    public int getItemViewType(int position) {
        if (position == 1)
            return 1;
        return 0;

    }*/

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MainViewHolder holder, int position) {
        float price = carts.get(position).getPrice();

        int quantity = carts.get(position).getQuantity();
        holder.cokeprice.setText(String.valueOf(price * quantity) + "$");
        holder.quantity.setText(String.valueOf(quantity));
        holder.coketitle.setText(carts.get(position).getTitle());

        Glide.with(context).load(carts.get(position).getImages()).into(holder.cartimage);
        updateprice();

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogingScreen.dbHelper.deleteItem(String.valueOf(carts.get(holder.getAdapterPosition()).getId()));
                MyCartScreen.sum-=carts.get(holder.getAdapterPosition()).getPrice()*Integer.parseInt(holder.quantity.getText().toString());
                carts.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                updateprice();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = carts.get(holder.getAdapterPosition()).getQuantity();
                quantity = quantity + 1;
                float price = carts.get(holder.getAdapterPosition()).getPrice();
                MyCartScreen.sum+=price*quantity;
                holder.cokeprice.setText(String.valueOf(price * quantity) + "$");
                holder.quantity.setText(String.valueOf(quantity));
                carts.get(holder.getAdapterPosition()).setQuantity(quantity);

                updatedb(Integer.toString(carts.get(holder.getAdapterPosition()).getId()),
                        carts.get(holder.getAdapterPosition()).getTitle(),
                        price,
                        carts.get(holder.getAdapterPosition()).getDescription(),
                        carts.get(holder.getAdapterPosition()).getCategory(),
                        carts.get(holder.getAdapterPosition()).getImages(),
                        Integer.toString(carts.get(holder.getAdapterPosition()).getQuantity())


                );
                notifyItemChanged(holder.getAdapterPosition());
                updateprice();

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carts.get(holder.getAdapterPosition()).getQuantity() > 1) {
                    int quantity = carts.get(holder.getAdapterPosition()).getQuantity();
                    quantity = quantity - 1;
                    float price = carts.get(holder.getAdapterPosition()).getPrice();
                    MyCartScreen.sum-=price*quantity;
                    holder.cokeprice.setText(String.valueOf(price * quantity) + "$");
                    holder.quantity.setText(String.valueOf(quantity));
                    carts.get(holder.getAdapterPosition()).setQuantity(quantity);
                    updatedb(Integer.toString(carts.get(holder.getAdapterPosition()).getId()),
                            carts.get(holder.getAdapterPosition()).getTitle(),
                            price,
                            carts.get(holder.getAdapterPosition()).getDescription(),
                            carts.get(holder.getAdapterPosition()).getCategory(),
                            carts.get(holder.getAdapterPosition()).getImages(),
                            Integer.toString(carts.get(holder.getAdapterPosition()).getQuantity())


                    );
                    notifyItemChanged(holder.getAdapterPosition());
                    updateprice();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
    public  void updateprice()
    {
        totalprice=0;
        totalquantity=0;
        for(int i=0;i<carts.size();i++)
        {
            totalprice=totalprice + carts.get(i).getPrice()*carts.get(i).getQuantity();
            totalquantity+=carts.get(i).getQuantity();
        }
        Helper.setTotalprice(totalprice);
        Helper.setTotalquantity(totalquantity);
        //Mycart.pricesetter();
    }
    public void updatedb(String id, String title,float price,String description, String category,String images,String quantity)
    {
        String userid=Integer.toString( LogingScreen.globaluser.getId());
        String email= LogingScreen.globaluser.getEmail();

        LogingScreen.dbHelper.UpdateItem(userid,email,id,title,price,description,category,images,quantity);
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{
        TextView coketitle,cokedes,cokeprice,quantity;
        ImageView cartimage,remove;
        ImageButton add,minus;
        CardView card3;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimage=itemView.findViewById(R.id.cartimage);
            coketitle=itemView.findViewById(R.id.coketitle);
            cokedes=itemView.findViewById(R.id.cokedes);
            cokeprice=itemView.findViewById(R.id.cokeprice);
            quantity=itemView.findViewById(R.id.quantity);
            add=itemView.findViewById(R.id.add);
            minus=itemView.findViewById(R.id.minus);
            remove=itemView.findViewById(R.id.remove);
            card3=itemView.findViewById(R.id.card3);

        }
    }


}
