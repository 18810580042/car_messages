package com.example.car_message.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.car_message.R;
import com.example.car_message.utils.ButtonUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PayPhoneAdapter extends RecyclerView.Adapter<PayPhoneAdapter.PhoneHolder> {

    Context context;
    int[] imglist;

    public PayPhoneAdapter(Context context, int[] imglist) {
        this.context = context;
        this.imglist = imglist;
    }

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_payphone, null);
        PhoneHolder holder = new PhoneHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, final int position) {
        if (imglist.length!=0){
            Glide.with(context).load(imglist[position])
                //    .placeholder(R.drawable.ucrop_ic_video_play)//加载成功前显示图片
                    .error(R.drawable.ucrop_ic_delete_photo)//异常
                    .fallback(R.drawable.ucrop_ic_video_play)//url为null显示                    .into(holder.pay_phone);
                    .into(holder.pay_phone);
        }
        holder.pay_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ButtonUtils.isFastDoubleClick()){
                    if (showphone!=null){
                        showphone.Lookpicture(imglist[position]);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imglist.length;
    }

    public class PhoneHolder extends RecyclerView.ViewHolder {

        private ImageView pay_phone;

        public PhoneHolder(@NonNull View itemView) {
            super(itemView);
            pay_phone = itemView.findViewById(R.id.item_pay_phone);

        }
    }

    Showphone showphone;

    public void setShowphone(Showphone showphone) {
        this.showphone = showphone;
    }

    public interface Showphone{
        void Lookpicture(int url);
    }
}
