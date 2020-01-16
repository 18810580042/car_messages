package com.example.car_message.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.car_message.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MinervAdapter extends RecyclerView.Adapter<MinervAdapter.MyViewHolder> {

    List<String> listdata;
    Context context;
    int [] listimg;
    public MinervAdapter(Context context, List<String> listdata, int[] img) {
        this.context=context;
        this.listdata=listdata;
        listimg=img;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout_mine,null);
        MyViewHolder myViewHolder = new MyViewHolder(context,inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (listdata != null && listdata.size()>0) {
            Log.i("tag",position+"=====");
            holder.item_mine_info.setText(listdata.get(position));
            Glide.with(context).load(listimg[position]).into(holder.img_max);
        }
        if (mineClick!=null){
             holder.item_mine_info.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     mineClick.ShowMine(position);
                 }
             });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_mine_info;
        ImageView img_max;

        public MyViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            item_mine_info = itemView.findViewById(R.id.item_mine_text);
            img_max = itemView.findViewById(R.id.mine_img_max);
        }
    }

   public MineClick mineClick;

    public void setMineClick(MineClick mineClick) {
        this.mineClick = mineClick;
    }

    public interface MineClick{
        void ShowMine(int position);
    }
}

