package com.example.car_message.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OutofAdapter extends RecyclerView.Adapter<OutofAdapter.OutofHolder> {

    String[] stringlist = {"冀A81CD1", "冀A81CD2", "冀A81CD3"};
    Context context;

    public OutofAdapter(Context applicationContext) {
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public OutofHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.outof_item, null);
        OutofHolder holder = new OutofHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutofHolder holder, int position) {
        holder.carnumber.setText(stringlist[position]);
    }

    @Override
    public int getItemCount() {
        return stringlist.length;
    }

    class OutofHolder extends RecyclerView.ViewHolder {

        private TextView carnumber;
        private TextView number;
        private ImageView item_image;
        private TextView outof_time;
        private TextView address;

        public OutofHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.item_outof_number);
            carnumber = itemView.findViewById(R.id.item_outof_carnumber);
            item_image = itemView.findViewById(R.id.outof_itemImage);
            outof_time = itemView.findViewById(R.id.item_outof_time);
            address = itemView.findViewById(R.id.item_outof_address);

        }
    }
}
