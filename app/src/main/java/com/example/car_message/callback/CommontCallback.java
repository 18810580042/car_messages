package com.example.car_message.callback;

/**
 * 张鑫
 * by .2017/3/12 16:35
 */
public interface CommontCallback<T> extends DataCallback {
    void onSuccess(T data);
}
