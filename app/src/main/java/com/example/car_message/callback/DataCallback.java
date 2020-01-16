package com.example.car_message.callback;

/**
 * 张鑫
 * by .2017/3/12 16:15
 */
public interface DataCallback {
    void onBefore();
    void onError(String errorMsg);
    void onComplete();
}