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

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupTaskAdapter extends RecyclerView.Adapter<GroupTaskAdapter.MyHolder> {

    Context context;
    int[] imglist;
    String[] titlelist;
    String[] infostring;

    public GroupTaskAdapter(Context context, int[] imglist, String[] titlelist, String[] infostring) {
        this.context = context;
        this.imglist = imglist;
        this.titlelist = titlelist;
        this.infostring = infostring;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_grouptask, null);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        Glide.with(context).load(imglist[position]).into(holder.img_join);
        holder.group_tasktitle.setText(titlelist[position]);
        holder.task_join_info.setText(infostring[position]);
        holder.group_task_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClick!=null){
                    itemClick.ShowIntent(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titlelist.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private   ImageView img_join;
        private   TextView group_tasktitle;
        private   TextView task_join_info;
        private   View group_task_item;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img_join = itemView.findViewById(R.id.img_join);
            group_tasktitle = itemView.findViewById(R.id.grouptask_join);
            task_join_info = itemView.findViewById(R.id.task_join_info);
            group_task_item = itemView.findViewById(R.id.group_task_item);
        }
    }

    ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick{
        void ShowIntent(int position);
    }
}
