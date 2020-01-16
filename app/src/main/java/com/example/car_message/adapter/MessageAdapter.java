package com.example.car_message.adapter;

import android.content.Context;
import android.media.Image;
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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    Context context;
    List<String> messagelist;
    int[] imglist;

    public MessageAdapter(Context context, List<String> messagelist, int[] imglist) {
        this.context = context;
        this.messagelist = messagelist;
        this.imglist = imglist;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        MessageViewHolder holder = new MessageViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.messgetext.setText(messagelist.get(position));
        Glide.with(context).load(imglist[position]).into(holder.img_message);
    }

    @Override
    public int getItemCount() {
        return messagelist.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView messgetext;
        private ImageView img_message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messgetext = itemView.findViewById(R.id.item_message_text);
            img_message = itemView.findViewById(R.id.itme_message_img);

        }
    }
}
