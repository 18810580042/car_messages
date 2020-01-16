package com.example.car_message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.utils.ButtonUtils;


import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//任务  界面 适配器
public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningHolder> {

    Context context;
    String[] stringlist = {"冀A81CD1", "冀A81CD2", "冀A81CD3"};

    public WarningAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WarningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_warning, null);
        WarningHolder holder = new WarningHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WarningHolder holder, final int position) {
        holder.warning_carnumber.setText(stringlist[position]);
        holder.check_weight_name.setText("交车人: 张三");
        holder.actual_phonenumber.setText("交车人电话: 14012739283319");
        holder.Overload_site_time.setText("交车时间: 2019.08.28 08:30:20");
        holder.warning_time.setText("2019.04.30 18:20:20");
        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ButtonUtils.isFastDoubleClick()){
                    if (showInfo!=null){
                        showInfo.Seesomething(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringlist.length;
    }

    class WarningHolder extends RecyclerView.ViewHolder {

        private TextView Overload_site_time;
        private TextView actual_phonenumber;
        private TextView check_weight_name;
        private TextView warning_time;
        private TextView warning_carnumber;
        private final TextView view_details;

        public WarningHolder(@NonNull View itemView) {
            super(itemView);
            //车牌号
            warning_carnumber = itemView.findViewById(R.id.warning_carnumber);
            //时间
            warning_time = itemView.findViewById(R.id.item_warning_time);
            //交车人
            check_weight_name = itemView.findViewById(R.id.check_weight_name);
            //交车人电话
            actual_phonenumber = itemView.findViewById(R.id.actual_phonenumber);
            //交车时间
            Overload_site_time = itemView.findViewById(R.id.Overload_site_time);
            //点击详情
            view_details = itemView.findViewById(R.id.view_details);

        }
    }

    ShowInfo showInfo;

    public void setShowInfo(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    public interface ShowInfo{
       void Seesomething(int position);
    }
}
