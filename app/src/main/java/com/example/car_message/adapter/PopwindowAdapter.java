package com.example.car_message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.car_message.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopwindowAdapter extends RecyclerView.Adapter<PopwindowAdapter.MyViewHolder> {

    Context context;

    public PopwindowAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_item_show, null);
        MyViewHolder holder=new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         holder.name.setText("车牌号:");
         holder.info.setText("冀A34533");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private   TextView info;
        private   TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pop_item_name);
            info = itemView.findViewById(R.id.pop_item_info);
        }
    }
}
