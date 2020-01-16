package com.example.car_message.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.utils.ButtonUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.MyHolder> {

    Context context;
    String[] titlelist;
    String[] stringlist;

    public JoinAdapter(Context context, String[] titlelist, String[] stringlist) {
        this.context = context;
        this.titlelist = titlelist;
        this.stringlist = stringlist;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_join, null);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        if ("交车照片".equals(titlelist[position])) {
            holder.imgback_join.setVisibility(View.VISIBLE);
        }
        holder.name.setText(titlelist[position]);
        holder.join_info.setText(stringlist[position]);
        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ButtonUtils.isFastDoubleClick()){
                    if (showpicture!=null){
                        showpicture.LookPicture(titlelist[position]);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringlist.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView imgback_join;
        private TextView name;
        private TextView join_info;
        private View item_layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            item_layout = itemView.findViewById(R.id.item_join_layout);
            imgback_join = itemView.findViewById(R.id.item_imgback_join);
            name = itemView.findViewById(R.id.item_join_name);
            join_info = itemView.findViewById(R.id.item_join_info);
        }
    }

    Showpicture showpicture;

    public void setShowpicture(Showpicture showpicture) {
        this.showpicture = showpicture;
    }

    public interface Showpicture{
        void LookPicture(String position);
    }
}
