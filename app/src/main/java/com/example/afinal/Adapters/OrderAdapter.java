//package com.example.afinal.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.data.localData.Cart;
import com.example.afinal.data.localData.Order;
import com.example.afinal.Helpers.Helper;

import java.util.ArrayList;

/*public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{
    Context ctx;
    ArrayList<Order> OrderList;

    public OrderAdapter(Context ctx, ArrayList<Order> orderList) {
        this.ctx = ctx;
        OrderList = orderList;
    }

    /*@NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.ordercell,parent,false);
        OrderAdapter.MyViewHolder viewHolder = new OrderAdapter.MyViewHolder(recyclerView);

        //return viewHolder;
    }

    /*@Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        holder.index.setText("Order number:"+OrderList.get(position).getIndex());
        holder.totalprice.setText(  OrderList.get(position).getPrice()+"$");
        holder.titles.setText(OrderList.get(position).getTitles());


    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    /*public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView index,totalprice,titles;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            index=itemView.findViewById(R.id.orderindex);
            totalprice=itemView.findViewById(R.id.cartitemprice);
            titles=itemView.findViewById(R.id.cartitemnames);
        }
    }
*/
