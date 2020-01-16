package com.example.car_message.dialog;

import android.app.Dialog;
import android.content.Context;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;

import androidx.annotation.NonNull;


public class LoadingDialog extends Dialog {
  private ImageView loadingImg;
  private Animation animation;
  private TextView loadingText;
  private boolean isClosedByOutSide;

  public LoadingDialog(Context context,boolean isClosedByOutSide) {
    super(context, R.style.loading_dialog);
    this.isClosedByOutSide = isClosedByOutSide;
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.progressbar_layout, null);// 得到加载view
    View loadingLayout = view.findViewById(R.id.ll_progressbar);

    loadingText = (TextView) view.findViewById(R.id.tv_loading);
    loadingImg = (ImageView) view.findViewById(R.id.iv_Loading);
    animation = AnimationUtils.loadAnimation(context, R.anim.rotate);

    this.setCanceledOnTouchOutside(isClosedByOutSide);
    this.setContentView(loadingLayout);
    this.getWindow().getAttributes().gravity = Gravity.CENTER;
    this.setCanceledOnTouchOutside(false);
  }


  @Override
  public void onWindowFocusChanged(boolean hasFocus) {

  }

  public void show(String str) {
    loadingImg.startAnimation(animation);
    if(!TextUtils.isEmpty(str)){
      loadingText.setText(str);
      loadingText.setVisibility(View.VISIBLE);
    }else {
      loadingText.setVisibility(View.GONE);
    }
    loadingImg.startAnimation(animation);
    super.show();
  }
  @Override
  public void show(){
    loadingImg.startAnimation(animation);
    super.show();
  }

  @Override
  public void dismiss() {
    loadingImg.clearAnimation();
    super.dismiss();

  }

  @Override
  public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
    if(!isClosedByOutSide){
      return false;
    }else {
      return super.onKeyDown(keyCode, event);
    }

  }
}