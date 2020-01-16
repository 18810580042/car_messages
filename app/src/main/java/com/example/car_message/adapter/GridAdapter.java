package com.example.car_message.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.car_message.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {
    Context context;
    String[] namelist = {"定位", "轨迹回放", "超载报警", "远程调试", "设备故障"};
    int[] imglist = {R.mipmap.location, R.mipmap.playback, R.mipmap.warning,
            R.mipmap.far_away, R.mipmap.work_out};

    public GridAdapter(Context applicationContext) {
        context = applicationContext;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_grid, null);
        GridHolder holder = new GridHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, final int position) {
        Glide.with(context).load(imglist[position]).into(holder.grid_image);
        holder.grid_text.setText(namelist[position]);
        if (gridClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridClick.ShowIntent(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return namelist.length;
    }

    class GridHolder extends RecyclerView.ViewHolder {

        private ImageView grid_image;
        private TextView grid_text;

        public GridHolder(@NonNull View itemView) {
            super(itemView);
            grid_image = itemView.findViewById(R.id.grid_image);
            grid_text = itemView.findViewById(R.id.grid_text);
        }
    }

    GridClick gridClick;

    public void setGridClick(GridClick gridClick) {
        this.gridClick = gridClick;
    }

    public interface GridClick {
        void ShowIntent(int i);
    }
}
